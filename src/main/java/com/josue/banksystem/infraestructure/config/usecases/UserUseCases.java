package com.josue.banksystem.infraestructure.config.usecases;

import com.josue.banksystem.application.in.user.CreateUser;
import com.josue.banksystem.application.in.user.FindUserById;
import com.josue.banksystem.application.in.user.GetUsers;
import com.josue.banksystem.application.in.user.UpdateUser;
import com.josue.banksystem.application.out.RoleRepository;
import com.josue.banksystem.application.out.UserRepository;
import com.josue.banksystem.application.usecases.user.CreateUserInteractor;
import com.josue.banksystem.application.usecases.user.FindUserByIdInteractor;
import com.josue.banksystem.application.usecases.user.GetUsersInteractor;
import com.josue.banksystem.application.usecases.user.UpdateUserInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserUseCases {

    @Bean
    public GetUsers getUsersInteractor(UserRepository userRepository) {
        return new GetUsersInteractor(userRepository);
    }

    @Bean
    public CreateUser createUserInteractor(UserRepository userRepository, RoleRepository roleRepository) {
        return new CreateUserInteractor(userRepository, roleRepository);
    }

    @Bean
    public UpdateUser updateUserInteractor(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new UpdateUserInteractor(userRepository, passwordEncoder);
    }

    @Bean
    public FindUserById findUserByIdInteractor(UserRepository userRepository) {
        return new FindUserByIdInteractor(userRepository);
    }


}
