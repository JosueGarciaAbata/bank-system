package com.josue.banksystem.application.in.user;

import com.josue.banksystem.domain.model.User;

public interface UpdateUser {

    User update(Long id, User user);
}
