package com.josue.banksystem.infraestructure.adapters.in.rest.response.client;

import com.josue.banksystem.infraestructure.adapters.in.rest.response.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientAndUserResponse {

    private Long id;
    private String direction;
    private String name;
    private String lastname;
    private UserResponse user;

}

