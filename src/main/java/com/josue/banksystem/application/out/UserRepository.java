package com.josue.banksystem.application.out;

import com.josue.banksystem.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findIncludingDeleted(Long id);
    User save(User user);
    User update(User user, Long id);
    void delete(Long id);
    void restore(User user);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
}
