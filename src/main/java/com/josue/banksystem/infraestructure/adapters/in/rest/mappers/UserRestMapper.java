package com.josue.banksystem.infraestructure.adapters.in.rest.mappers;

import com.josue.banksystem.domain.models.User;
import com.josue.banksystem.infraestructure.adapters.in.rest.requests.user.CreateUserRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.requests.user.UpdateUserRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.responses.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRestMapper {

    User toUser(CreateUserRequest request);
    User toUser(UpdateUserRequest request);

    UserResponse toUserRespose(User user);

}
