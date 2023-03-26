package br.edu.ifpb.upcensus.infrastructure.http.response.service;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import br.edu.ifpb.upcensus.infrastructure.util.ServerUtils;
import br.edu.ifpb.upcensus.infrastructure.util.StringUtils;
import br.edu.ifpb.upcensus.presentation.shared.response.Response;
import br.edu.ifpb.upcensus.presentation.shared.response.ResponseBuilder;

@Service
@RequestScope
public class ResponseServiceImpl implements ResponseService {
	
	private final HttpServletResponse httpResponse;
	private final HttpServletRequest httpRequest;
	
	public ResponseServiceImpl(
			final HttpServletResponse httpResponse, 
			final HttpServletRequest httpRequest
		) {
		super();
		this.httpResponse = httpResponse;
		this.httpRequest = httpRequest;
	}



	@Override
	public <T> Response<T> buildResponse(T data, HttpStatus status, String path, Object... values) {
		ResponseBuilder<T> response = new ResponseBuilder<>(data);
		response.endpoint(ServerUtils.getUri(httpRequest));
		response.status(status);
		if (StringUtils.notEmpty(path)) {
			URI uri = ServerUtils.setupUri(httpResponse, path, values);
			
			response.addLink(uri.toASCIIString());
		}
		
		return response.build();
	}

}
