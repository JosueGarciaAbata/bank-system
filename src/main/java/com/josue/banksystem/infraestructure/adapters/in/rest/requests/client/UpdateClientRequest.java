package com.josue.banksystem.infraestructure.adapters.in.rest.requests.client;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateClientRequest {

    @NotBlank(message = "Direction must not be null and empty.")
    private String direction;

    @NotBlank(message = "Name must not be null and empty.")
    private String name;

    @NotBlank(message = "Lastname must not be null and empty.")
    private String lastname;
}
