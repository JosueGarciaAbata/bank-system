package com.josue.banksystem.application.usecases.role;

import com.josue.banksystem.application.in.role.FindByNameRole;
import com.josue.banksystem.application.out.RoleRepository;
import com.josue.banksystem.domain.exception.RoleNotFoundException;
import com.josue.banksystem.domain.models.Role;

public class FindByNameRoleInteractor implements FindByNameRole {

    private RoleRepository roleRepository;

    public FindByNameRoleInteractor(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException("Role with the name " + name + " not found."));
    }
}
