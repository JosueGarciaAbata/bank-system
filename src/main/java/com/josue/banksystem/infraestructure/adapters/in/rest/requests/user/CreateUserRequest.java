package com.josue.banksystem.infraestructure.adapters.in.rest.requests.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest {

    private String email;
    private String password;
    private boolean admin;
    private boolean employee;

}
