package com.josue.banksystem.application.usecases.user;

import com.josue.banksystem.application.in.user.CreateUser;
import com.josue.banksystem.application.out.RoleRepository;
import com.josue.banksystem.application.out.UserRepository;
import com.josue.banksystem.domain.exception.RoleNotFoundException;
import com.josue.banksystem.domain.exception.UserEmailAlreadyTaken;
import com.josue.banksystem.domain.model.Role;
import com.josue.banksystem.domain.model.User;
import com.josue.banksystem.infraestructure.common.UseCase;

public class CreateUserInteractor implements CreateUser {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public  CreateUserInteractor(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @UseCase
    @Override
    public User create(User user) {

        Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RoleNotFoundException("The role ROLE_ADMIN could not be found"));
        user.getRoles().add(userRole);

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserEmailAlreadyTaken("Email already taken.");
        }

        if (user.isAdmin()) {
            Role admin = roleRepository.findByName("ROLE_ADMIN").orElseThrow(() -> new RoleNotFoundException("The role ROLE_ADMIN could not be found"));
            user.getRoles().add(admin);
        }

        if (user.isEmployee()) {
            Role employee = roleRepository.findByName("ROLE_EMPLOYEE").orElseThrow(() -> new RoleNotFoundException("The role ROLE_ADMIN could not be found"));
            user.getRoles().add(employee);
        }

        return userRepository.save(user);
    }
}
