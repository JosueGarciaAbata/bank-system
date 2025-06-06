package com.josue.banksystem.application.usecases.user;

import com.josue.banksystem.application.in.user.UpdateUser;
import com.josue.banksystem.application.out.ClientRepository;
import com.josue.banksystem.application.out.UserRepository;
import com.josue.banksystem.domain.exception.UserEmailAlreadyTaken;
import com.josue.banksystem.domain.exception.UserNotFoundException;
import com.josue.banksystem.domain.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UpdateUserInteractor implements UpdateUser {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public  UpdateUserInteractor(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User update(Long id, User user) {
        User toUpdate = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id=" + id));
        toUpdate.setRoles(user.getRoles());

        if (userRepository.existsByEmailAndIdNot(user.getEmail(), id)) {
            throw new UserEmailAlreadyTaken("Email Already Taken");
        }

        toUpdate.setEmail(user.getEmail());
        toUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(toUpdate);
    }
}
