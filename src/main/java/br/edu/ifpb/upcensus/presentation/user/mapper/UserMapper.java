package br.edu.ifpb.upcensus.presentation.user.mapper;

import org.mapstruct.Mapper;

import br.edu.ifpb.upcensus.domain.user.model.User;
import br.edu.ifpb.upcensus.domain.user.role.model.Role;
import br.edu.ifpb.upcensus.domain.user.service.UserService;
import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.user.request.UserRequest;
import br.edu.ifpb.upcensus.presentation.user.response.UserResponse;


@Mapper(
		imports = Role.class,
		config = MapStructConfig.class,
		uses = UserService.class
	)
public interface UserMapper extends BaseMapper<User, UserRequest, UserResponse>{

}
