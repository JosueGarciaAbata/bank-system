package com.josue.banksystem.infraestructure.adapters.in.rest.response.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientResponse {

    private Long id;
    private String direction;
    private String name;
    private String lastname;

}
