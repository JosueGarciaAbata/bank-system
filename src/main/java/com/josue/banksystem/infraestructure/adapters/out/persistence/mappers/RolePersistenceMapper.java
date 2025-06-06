package com.josue.banksystem.infraestructure.adapters.out.persistence.mappers;

import com.josue.banksystem.domain.model.Role;
import com.josue.banksystem.infraestructure.adapters.out.persistence.role.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolePersistenceMapper {

    RoleEntity toRoleEntity(Role role);
    Role toRole(RoleEntity roleEntity);
}
