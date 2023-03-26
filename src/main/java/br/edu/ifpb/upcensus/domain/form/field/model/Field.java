package br.edu.ifpb.upcensus.domain.form.field.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;

@Entity
@Table(name = "t_field", schema = "form")
@SequenceGenerator(name = "t_field_id_seq", schema = "form", sequenceName = "t_field_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Campo")
public class Field extends DomainModel<Long> {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_field_id_seq")
	private Long id;
    
    @NotNull
    @Column(name = "code", unique = true)
    @Size(min = 3, max = 128)
    private String code;
    
    @NotNull
    @Size(min = 3, max = 128)
    private String name;

    @Size(max = 512)
    private String description;
    
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
    	name = "t_field_characteristic",
    	schema = "form",
    	joinColumns = @JoinColumn(name = "id_field"),
    	inverseJoinColumns = @JoinColumn(name = "id_characteristic")
    )
    private List<Characteristic> characteristics;
    

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

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public List<Characteristic> getCharacteristics() {
		return characteristics;
	}
	
	public void setCharacteristics(List<Characteristic> characteristics) {
		this.characteristics = characteristics;
	}

	@Override
	public String toString() {
		return String.format(
				"{id: %s, code: %s, name: %s, description: %s, characteristics: %s, creationTime: %s}", id, code,
				name, description, characteristics, getCreationTime());
	}

	
	
	
	

}
