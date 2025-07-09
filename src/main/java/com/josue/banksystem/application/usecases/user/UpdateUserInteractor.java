package com.josue.banksystem.application.usecases.user;

import com.josue.banksystem.application.common.annotations.UseCase;
import com.josue.banksystem.application.in.user.UpdateUser;
import com.josue.banksystem.application.out.UserRepository;
import com.josue.banksystem.domain.exception.UserEmailAlreadyTaken;
import com.josue.banksystem.domain.exception.UserNotFoundException;
import com.josue.banksystem.domain.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UpdateUserInteractor implements UpdateUser {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public  UpdateUserInteractor(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @UseCase
    public User update(Long id, User user) {
        User toUpdate = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id=" + id));
        // Roles...
        toUpdate.setRoles(user.getRoles());

        if (userRepository.existsByEmailAndIdNot(user.getEmail(), id)) {
            throw new UserEmailAlreadyTaken("Email Already Taken");
        }

        // y correo siempre actualizados, pero...
        toUpdate.setEmail(user.getEmail());

        // la contrasena solo si viene y es distinta.
        String rawPassword = user.getPassword();
        if (rawPassword != null && !rawPassword.isBlank()) {
            String hashedPassword = toUpdate.getPassword();
            if (!passwordEncoder.matches(rawPassword, hashedPassword)) { // Son diferentes...
                toUpdate.setPassword(passwordEncoder.encode(rawPassword)); // Asi que se actualiza.
            }
        }

        // Y se guarda.
        return userRepository.update(toUpdate, toUpdate.getId());
    }
}
