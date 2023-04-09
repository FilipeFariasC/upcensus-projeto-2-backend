package br.edu.ifpb.upcensus.business.form.characteristic.service;

import org.springframework.stereotype.Service;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.characteristic.service.CharacteristicService;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.form.CharacteristicRepository;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {

	private final CharacteristicRepository characteristicsRepository;
	
	public CharacteristicServiceImpl(final CharacteristicRepository characteristicsRepository) {
		super();
		this.characteristicsRepository = characteristicsRepository;
	}

	@Override
	public CharacteristicRepository getRepository() {
		return characteristicsRepository;
	}

	@Override
	public Class<Characteristic> getDomainClass() {
		return Characteristic.class;
	}

}
