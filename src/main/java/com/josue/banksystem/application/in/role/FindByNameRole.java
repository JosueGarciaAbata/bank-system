package com.josue.banksystem.application.in.role;

import com.josue.banksystem.domain.models.Role;

public interface FindByNameRole {

    Role findByName(String name);
}
