package com.josue.banksystem.infraestructure.adapters.in.rest.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequest {

    @Email(message = "Format invalid.")
    private String email;

    @NotBlank(message = "Password must not be null and empty.")
    private String password;

    private boolean admin;
    private boolean employee;
    private boolean user;
}
