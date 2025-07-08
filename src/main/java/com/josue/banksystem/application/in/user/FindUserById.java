package com.josue.banksystem.application.in.user;

import com.josue.banksystem.domain.models.User;

public interface FindUserById {

    User findById(Long id);
}
