package br.edu.ifpb.upcensus.domain.module.module.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;

@Entity
@Table(name = "t_metadata", schema = "module", uniqueConstraints = @UniqueConstraint(columnNames = { "code" }))
@SequenceGenerator(name = "t_metadata_id_seq", schema = "module", sequenceName = "t_metadata_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Metadado")
public class Metadata extends DomainModel<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_metadata_id_seq")
	private Long id;
	
	@NotNull
    @Column(name = "code", unique = true)
    @Size(min = 3, max = 128)
    private String code;
	
    @NotNull
    @Size(min = 3, max = 128)
    private String name;

	@NotNull
	@Size(max = 2048)
	private String value;

	public static Metadata of(final String code, final String name, final String value) {
		final Metadata metadata = new Metadata();

		metadata.setCode(code);
		metadata.setName(name);
		metadata.setValue(value);

		return metadata;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(code, id, name, value);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Metadata other = (Metadata) obj;
		return Objects.equals(code, other.code) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return String.format("{id: %s, code: %s, name: %s, value: %s}", id, code, name, value);
	}

	
	
}
