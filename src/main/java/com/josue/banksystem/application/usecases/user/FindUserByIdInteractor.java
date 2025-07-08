package com.josue.banksystem.application.usecases.user;

import com.josue.banksystem.application.in.user.FindUserById;
import com.josue.banksystem.application.out.UserRepository;
import com.josue.banksystem.domain.exception.UserNotFoundException;
import com.josue.banksystem.domain.models.User;

public class FindUserByIdInteractor implements FindUserById {

    private final UserRepository userRepository;

    public FindUserByIdInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id=" + id));
    }
}
