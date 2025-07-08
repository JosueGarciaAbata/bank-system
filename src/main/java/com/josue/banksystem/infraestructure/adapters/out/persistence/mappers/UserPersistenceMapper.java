package com.josue.banksystem.infraestructure.adapters.out.persistence.mappers;

import com.josue.banksystem.domain.models.User;
import com.josue.banksystem.infraestructure.adapters.out.persistence.user.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { RolePersistenceMapper.class })
public interface UserPersistenceMapper {

    UserEntity toUserEntity(User user);
    User toUser(UserEntity userEntity);
}
