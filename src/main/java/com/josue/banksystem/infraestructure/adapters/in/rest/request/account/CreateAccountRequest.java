package com.josue.banksystem.infraestructure.adapters.in.rest.request.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateAccountRequest {

    @NotBlank(message = "Account number must not be null and empty.")
    @JsonProperty("account_number")
    private String accountNumber;

    @NotNull(message = "Balance must not be null.")
    private Double balance;

    @NotBlank(message = "Type must not be null and empty.")
    private String type;

    @NotNull(message = "Client id must not be null and empty.")
    @JsonProperty("client_id")
    private Long clientId;

}
