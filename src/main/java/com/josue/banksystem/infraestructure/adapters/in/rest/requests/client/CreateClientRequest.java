package com.josue.banksystem.infraestructure.adapters.in.rest.requests.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.josue.banksystem.application.common.annotations.ValidDni;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateClientRequest {

    @ValidDni
    @NotBlank(message = "DNI is required")
    private String dni;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Lastname is required")
    private String lastname;

    @NotBlank(message = "Address is required")
    private String direction;

    @NotNull(message = "User ID is required")
    @JsonProperty(value = "user_id")
    private Long userId;

}
