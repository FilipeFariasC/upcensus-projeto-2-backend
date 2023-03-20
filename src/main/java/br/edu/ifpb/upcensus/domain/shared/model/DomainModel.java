package br.edu.ifpb.upcensus.domain.shared.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import br.edu.ifpb.upcensus.domain.shared.exception.InvalidDomainModelException;

@MappedSuperclass
public abstract class DomainModel <I extends Serializable> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
    @Column(name = "creation_time", nullable = false)
	private LocalDateTime creationTime;
	
	public abstract I getId();
	
	public void validate() throws InvalidDomainModelException {};
	
	
	public void defineCreationTime() {
		setCreationTime(LocalDateTime.now());
	}
	
	public void register() {
		defineCreationTime();
	}
	
	
	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public LocalDateTime getCreationTime() {
		return this.creationTime;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(getId(), creationTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainModel other = (DomainModel) obj;
		return Objects.equals(getId(), other.getId()) && Objects.equals(getCreationTime(), other.getCreationTime());
	}
	
	
	
}
