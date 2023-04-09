package br.edu.ifpb.upcensus.infrastructure.http.response.service;

import org.springframework.http.HttpStatus;

import br.edu.ifpb.upcensus.presentation.shared.response.Response;

public interface ResponseService {
	public <T> Response<T> buildResponse(T data, HttpStatus status, String path, Object... values);
	default public <T> Response<T> buildResponse(T data, HttpStatus status) {
		return buildResponse(data, status, null);
	}
}
