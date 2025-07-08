package com.josue.banksystem.infraestructure.adapters.in.rest.requests.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateClientRequest {

    private String name;
    private String lastname;
    private String direction;

    @JsonProperty(value = "user_id")
    private Long userId;


}
