package com.josue.banksystem.infraestructure.adapters.in.rest.mapper;

import com.josue.banksystem.domain.model.User;
import com.josue.banksystem.infraestructure.adapters.in.rest.request.user.CreateUserRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.request.user.UpdateUserRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.response.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRestMapper {

    User toUser(CreateUserRequest request);
    User toUser(UpdateUserRequest request);

    UserResponse toUserRespose(User user);

}
