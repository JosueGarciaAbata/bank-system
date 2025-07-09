package com.josue.banksystem.infraestructure.adapters.in.rest.requests.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdateUserRequest {

    @Email(message = "Format invalid.")
    private String email;

    // The user password is not required to be updated.
    private String password;

    private boolean admin;
    private boolean employee;
    private boolean user;
}
