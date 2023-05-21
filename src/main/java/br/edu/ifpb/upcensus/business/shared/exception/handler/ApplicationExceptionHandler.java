package br.edu.ifpb.upcensus.business.shared.exception.handler;


import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainException;
import br.edu.ifpb.upcensus.infrastructure.http.response.internationalization.MessageSourceService;
import br.edu.ifpb.upcensus.infrastructure.util.MessageKeys;
import br.edu.ifpb.upcensus.infrastructure.util.ObjectUtils;
import br.edu.ifpb.upcensus.infrastructure.util.ServerUtils;
import br.edu.ifpb.upcensus.infrastructure.util.StringUtils;
import br.edu.ifpb.upcensus.presentation.shared.response.ResponseBuilder;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	private final MessageSourceService messageSourceService;
	public ApplicationExceptionHandler(
		final MessageSourceService messageSourceService,
		@Value("${logging.runtimeException}") final boolean logRuntimeException
	) {
		super();
		this.messageSourceService = messageSourceService;
	}
	@ExceptionHandler({ br.edu.ifpb.upcensus.infrastructure.exception.DomainException.class })
    public ResponseEntity<Object> handleDomainModelException(br.edu.ifpb.upcensus.infrastructure.exception.DomainException exception, WebRequest request) {
		DomainException domainException = AnnotationUtils.findAnnotation(exception.getClass(), DomainException.class);
		
		Class<?> domainClass = exception.getClassObject();
		DomainDescriptor domainDescriptor = AnnotationUtils.findAnnotation(domainClass, DomainDescriptor.class);
		String className = Optional.ofNullable(domainDescriptor)
			.map(DomainDescriptor::name)
			.orElse(domainClass.getSimpleName());
		
		Object[] params = Stream.concat(Stream.of(className), Arrays.stream(exception.getParams())).toArray();
		
		return handleDomainException(exception, request, domainException, params);
	}
	
	@ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception, WebRequest request) {
		DomainException domainException = AnnotationUtils.findAnnotation(exception.getClass(), DomainException.class);
		
		if (ObjectUtils.nonNull(domainException)) {
			return handleDomainException(exception, request, domainException);	
		}

		return handleException(exception, BAD_REQUEST, request, MessageKeys.INVALID_REQUEST);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception,
            WebRequest request) {
		Exception cause = (Exception) exception.getCause();
        if (cause instanceof ConstraintViolationException) {
            return handleHibernateConstraintViolationException((ConstraintViolationException) exception.getCause(),request);
        }
        
        return handleException(exception, BAD_REQUEST, request, MessageKeys.INVALID_REQUEST);
    }

	@ExceptionHandler({ConstraintViolationException.class })
    public ResponseEntity<Object> handleHibernateConstraintViolationException(ConstraintViolationException exception,
            WebRequest request) {
        return handleException(exception, HttpStatus.BAD_REQUEST, request, MessageKeys.CONSTRAINT_PREFIX + exception.getConstraintName());

    }
	@ExceptionHandler({ javax.validation.ConstraintViolationException.class })
    public ResponseEntity<Object> handleValidationConstraintViolationException(
    		javax.validation.ConstraintViolationException exception,
            WebRequest request) {
		
		List<String> errors =exception.getConstraintViolations()
			.stream()
			.map(violation -> MessageFormat.format(violation.getMessage(), violation.getPropertyPath()))
			.collect(Collectors.toList());

        return handleExceptionInternal(exception,
        	new ResponseBuilder<>()
        		.status(BAD_REQUEST)
        		.endpoint(ServerUtils.getUri(request))
        		.errors(errors)
        		.build()
        	, new HttpHeaders(), BAD_REQUEST, request);
    }
	
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = getErrors(ex.getBindingResult());
        return handleExceptionInternal(
        	ex,
        	new ResponseBuilder<>()
        		.status(BAD_REQUEST)
        		.endpoint(ServerUtils.getUri(request))
        		.errors(errors)
        		.build(), 
        	headers, 
        	HttpStatus.BAD_REQUEST, 
        	request);
    }
	

	public ResponseEntity<Object> handleDomainException(Exception exception, WebRequest request, DomainException domainException, Object... messageParams) {
		ResponseBuilder<?> builder = new ResponseBuilder<>();
		
		builder.status(domainException.status());
		builder.endpoint(ServerUtils.getUri(request));
		
		builder.addError(messageSourceService.getMessage(domainException.key(), messageParams));
		
		if (domainException.append()) {
			builder.addError(exception.getMessage());
		}
		
		return handleExceptionInternal(exception, builder.build(), new HttpHeaders(), domainException.status(), request);
	}
	
	
	public ResponseEntity<Object> handleException(Exception exception, HttpStatus status, WebRequest request, String key) {
		String message = StringUtils.notEmpty(key) ? messageSourceService.getMessage(key) : null;
		exception.printStackTrace();
		
		return handleExceptionInternal(
			exception, 
			new ResponseBuilder<>()
				.endpoint(ServerUtils.getUri(request))
				.status(status)
				.addError(message)
				.addError(exception.getMessage())
				.build(), 
			new HttpHeaders(),
			status,
			request);
	}
	
	private List<String> getErrors(BindingResult bindingResult) {
		return bindingResult
			.getFieldErrors()
			.stream()
			.map(messageSourceService::getMessage)
			.collect(Collectors.toList());
			
	}
	
	
    @ExceptionHandler({ TransactionSystemException.class })
    public ResponseEntity<Object> handleRollbackException(TransactionSystemException ex, WebRequest request) {

        Exception exception = (Exception) ex.getCause().getCause();
        if (exception.getClass().equals(javax.validation.ConstraintViolationException.class)) {
            return this.handleValidationConstraintViolationException((javax.validation.ConstraintViolationException) exception, request);
        } else {
            return this.handleRuntimeException(ex, request);
        }
    }
	
}
