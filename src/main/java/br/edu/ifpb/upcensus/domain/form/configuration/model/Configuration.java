package br.edu.ifpb.upcensus.domain.form.configuration.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;

@Entity
@Table(name = "t_configuration", schema = "form")
@SequenceGenerator(name = "t_configuration_id_seq", schema = "form", sequenceName = "t_configuration_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Configuração")
public class Configuration extends DomainModel<Long> {
	
	private static final long serialVersionUID = 1L;
		
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_configuration_id_seq")
	private Long id;
    
    @NotNull
    @Column(name = "code", unique = true)
    @Size(min = 3, max = 128)
    private String code;
    
    @NotNull
    @Size(min = 3, max = 128)
    private String name;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "configuration")
    private Set<ConfigurationCharacteristic> characteristics;
    

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


	public Set<ConfigurationCharacteristic> getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(Set<ConfigurationCharacteristic> characteristics) {
		this.characteristics = characteristics;
	}
	
	
	
	@Override
	public String toString() {
		return String.format("{id: %s, code: %s, name: %s, characteristics: %s}", id, code, name, characteristics);
	}
	

}
