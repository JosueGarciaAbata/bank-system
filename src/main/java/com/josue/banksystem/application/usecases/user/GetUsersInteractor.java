package com.josue.banksystem.application.usecases.user;

import com.josue.banksystem.application.in.user.GetUsers;
import com.josue.banksystem.application.out.UserRepository;
import com.josue.banksystem.domain.models.User;

import java.util.List;

public class GetUsersInteractor implements GetUsers {

    private final UserRepository userRepository;

    public GetUsersInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
