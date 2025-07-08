package com.josue.banksystem.application.usecases.services;

import com.josue.banksystem.application.common.annotations.UseCase;
import com.josue.banksystem.application.in.client.CreateClient;
import com.josue.banksystem.application.in.user.CreateUser;
import com.josue.banksystem.application.in.services.RegisterNewClientUser;
import com.josue.banksystem.application.out.UserRepository;
import com.josue.banksystem.domain.exception.RoleNotFoundException;
import com.josue.banksystem.domain.exception.UserEmailAlreadyTaken;
import com.josue.banksystem.domain.model.Client;
import com.josue.banksystem.domain.model.Role;
import com.josue.banksystem.application.out.RoleRepository;
import com.josue.banksystem.domain.model.User;

import org.springframework.security.crypto.password.PasswordEncoder;

public class RegisterNewClientUserInteractor implements RegisterNewClientUser {

    private PasswordEncoder encoder;
    private CreateClient createClient;
    private CreateUser createUser;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final String DEFAULT_ROLE = "ROLE_USER";

    public RegisterNewClientUserInteractor(
            PasswordEncoder encoder,
            UserRepository userRepository,
            RoleRepository roleRepository,
            CreateClient createClient,
            CreateUser createUser
            ) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.createClient = createClient;
        this.createUser = createUser;
    }

    @UseCase
    @Override
    public Client execute(User user, Client client) {
        if (this.userRepository.existsByEmail(user.getEmail())) {
            throw new UserEmailAlreadyTaken("Email is  already taken.");
        }

        Role role = roleRepository.findByName(DEFAULT_ROLE).orElseThrow(() -> new RoleNotFoundException("Role not found"));
        user.getRoles().add(role);
        user.setEmployee(false);
        user.setAdmin(false);
        user.setEnabled(true);
        // createUser.execute(user);
        client.setUser(user);

        return createClient.create(client);
    }
}
