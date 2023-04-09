package br.edu.ifpb.upcensus.presentation.shared.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;

import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;
import br.edu.ifpb.upcensus.infrastructure.util.ObjectUtils;

public class ResponseBuilder<R>{
	private Response<R> response;
	public ResponseBuilder() {
		this.response = new Response<>();
	}
	public ResponseBuilder(R data) {
		this.response = new Response<>(data);
	}
	public ResponseBuilder<R> data(R data) {
		this.response.setData(data);
		return this;
	}
	
	public ResponseBuilder<R> status(HttpStatus status) {
		this.response.setStatus(status);
		return this;
	}

	public ResponseBuilder<R> endpoint(String endpoint) {
		this.response.setEndpoint(endpoint);
		return this;
	}

	public ResponseBuilder<R> links(List<String> links) {
		List<String> newLinks = new ArrayList<>();
		newLinks.addAll(links);
		this.response.setLinks(newLinks);
		return this;
	}


	public ResponseBuilder<R> addLink(String link) {
		if (CollectionUtils.isEmpty(this.response.getLinks())) {
			this.response.setLinks(Stream.of(link).collect(Collectors.toList()));
		} else {
			this.response.getLinks().add(link);
		}
		return this;
	}


	public ResponseBuilder<R> errors(List<String> errors) {
		List<String> newErrors = new ArrayList<>();
		newErrors.addAll(errors);
		this.response.setErrors(newErrors);
		return this;
	}


	public ResponseBuilder<R> addError(String error) {
		if (ObjectUtils.isNull(error)) {
			return this;
		}
		if (CollectionUtils.isEmpty(this.response.getErrors())) {
			this.response.setErrors(Stream.of(error).collect(Collectors.toList()));
		} else {
			this.response.getErrors().add(error);
		}
		return this;
	}
	
	public Response<R> build() {
		return this.response;
	}
	
}
