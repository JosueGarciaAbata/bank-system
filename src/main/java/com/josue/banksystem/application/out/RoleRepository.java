package com.josue.banksystem.application.out;

import com.josue.banksystem.domain.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    List<Role> findAll();
    Optional<Role> findById(Long id);
    Role save(Role user);
    Role update(Role user, Long id);
    void delete(Long id);
    Optional<Role> findByName(String name);
}
