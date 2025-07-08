package com.josue.banksystem.infraestructure.adapters.in.rest.requests.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.josue.banksystem.domain.enumerations.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Type must not be null and empty.")
    // Jackson convierte: "SAVINGS" a AccountType.SAVINGS (sino puede lanza excepcion).
    private AccountType type;

    @NotNull(message = "Client id must not be null and empty.")
    @JsonProperty("client_id")
    private Long clientId;

}
