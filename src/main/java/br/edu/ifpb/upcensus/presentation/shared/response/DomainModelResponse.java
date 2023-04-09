package br.edu.ifpb.upcensus.presentation.shared.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class DomainModelResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@JsonProperty("creation_time")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDateTime creationTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDateTime getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}
	
	@Override
	public String toString() {
		return String.format("{id: %s, creationTime: %s}", id, creationTime);
	}
	
}
