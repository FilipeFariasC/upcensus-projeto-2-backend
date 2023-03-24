package br.edu.ifpb.upcensus.presentation.shared.response;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(value = Include.NON_NULL)
public class Response<R> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String status;
	private String endpoint;
	private R data;
	private List<String> links;
	private List<String> errors;
	
	Response() {}
	Response(R data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = Objects.toString(status);
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public R getData() {
		return data;
	}
	public void setData(R data) {
		this.data = data;
	}
	public List<String> getLinks() {
		return links;
	}
	public void setLinks(List<String> links) {
		this.links = links;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	@Override
	public String toString() {
		return String.format("{status: %s, endpoint: %s, data: %s, links: %s, errors: %s}", status, endpoint, data,
				links, errors);
	}
	
}
