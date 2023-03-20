package br.edu.ifpb.upcensus.infrastructure.mapper;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

public interface BaseMapper <M, I, O> {
	
	M requestToModel(I request);
	O modelToResponse(M model);
	
	List<M> requestListToModelList(List<I> requests);
	List<O> modelListToResponseList(List<M> models);

	Set<M> requestSetToModelSet(Set<I> request);
	Set<O> modelSetToResponseSet(Set<M> models);

	default Page<M> requestPageToModelPage(Page<I> requests){
		return requests.map(this::requestToModel);
	}
	default Page<O> modelPageToResponsePage(Page<M> models){
		return models.map(this::modelToResponse);
	}
}
