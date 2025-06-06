package com.josue.banksystem.infraestructure.adapters.out.persistence.role;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleJpaRepository extends CrudRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
