package br.edu.ifpb.upcensus.infrastructure.http.response.internationalization;

import java.util.stream.Stream;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;
import br.edu.ifpb.upcensus.infrastructure.util.ObjectUtils;

@Service
public class MessageSourceServiceImpl implements MessageSourceService{
    private final MessageSource messageSource;

    public MessageSourceServiceImpl(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    public String getMessage(FieldError fieldError) {
        return messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
    }

	@Override
	public String getMessage(String key, Object... params) {
		Object[] filtered = params;
		if (CollectionUtils.notEmpty(params)) {
			filtered = Stream.of(params).filter(ObjectUtils::nonNull).toArray();
		}
        return messageSource.getMessage(key, filtered, LocaleContextHolder.getLocale());
	}
}
