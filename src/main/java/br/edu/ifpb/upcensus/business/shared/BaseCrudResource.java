package br.edu.ifpb.upcensus.business.shared;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.domain.shared.service.DomainService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.presentation.shared.response.Response;
/**
 * 
 * @author filipefariasc
 *
 * @param <M> Entidade do Domínio
 * @param <K> Tipo do identificador da entidade
 * @param <I> Request da entidade
 * @param <O> Response da entidade
 */
public abstract class BaseCrudResource<M extends DomainModel<K>, K extends Serializable, I, O> {
	
	protected abstract ResponseService getResponseService();
	protected abstract DomainService<M, K> getModelService();
	protected abstract BaseMapper<M, I, O> getDomainMapper();
	
	@GetMapping
	@ResponseStatus(OK)
	public Response<Page<O>> findAll(Pageable pageable) {
		Page<M> models = getModelService().findAll(pageable);
		Page<O> responses = models.map(getDomainMapper()::modelToResponse);
		
		return getResponseService().buildResponse(responses, OK);
	}
	
	@GetMapping(ApiEndpoints.ID)
	@ResponseStatus(OK)
	public Response<O> findById(@PathVariable K id) {
		M model = getModelService().findById(id);
		O response = getDomainMapper().modelToResponse(model);
		
		return getResponseService().buildResponse(response, OK);
	}

	
	@GetMapping(ApiEndpoints.ALL)
	@ResponseStatus(OK)
	public Response<List<O>> getAll() {
		List<M> model = getModelService().findAll();
		List<O> responses = getDomainMapper().modelListToResponseList(model);
		
		return getResponseService().buildResponse(responses, OK);
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Response<O> register(@RequestBody I request) {
		M model = getDomainMapper().requestToModel(request);
		M registered = getModelService().register(model);
		O response = getDomainMapper().modelToResponse(registered);
		
		return getResponseService().buildResponse(response, CREATED, ApiEndpoints.ID, model.getId());
	}
	
	@PutMapping(ApiEndpoints.ID)
	@ResponseStatus(OK)
	public Response<O> update(@PathVariable K id, @RequestBody I request) {
		M model = getDomainMapper().requestToModel(request);
		M registered = getModelService().update(id, model);
		O response = getDomainMapper().modelToResponse(registered);
		
		return getResponseService().buildResponse(response, OK);
	}
	
	@DeleteMapping(ApiEndpoints.ID)
	@ResponseStatus(NO_CONTENT)
	public Response<?> delete(@PathVariable K id) {
		this.getModelService().deleteById(id);
		
		return null;
	}
}
