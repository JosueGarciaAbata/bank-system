package com.josue.banksystem.infraestructure.adapters.in.rest.response.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.josue.banksystem.infraestructure.adapters.in.rest.response.moviment.MovimentResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AccountResponseWithMoviments {

    private Long id;

    @JsonProperty("account_number")
    private String accountNumber;
    private Double balance;
    private String type;

    @JsonProperty("client_id")
    private String clientId;

    private List<MovimentResponse> moviments;
}
