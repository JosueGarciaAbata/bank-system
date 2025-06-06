package com.josue.banksystem.infraestructure.adapters.in.rest.response.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountResponse {

    private Long id;

    @JsonProperty("account_number")
    private String accountNumber;
    private String type;
    private Double balance;

    @JsonProperty("client_id")
    private String clientId;
}
