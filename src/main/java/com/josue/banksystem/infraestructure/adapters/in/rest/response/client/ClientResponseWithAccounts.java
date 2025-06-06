package com.josue.banksystem.infraestructure.adapters.in.rest.response.client;

import com.josue.banksystem.infraestructure.adapters.in.rest.response.account.AccountResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ClientResponseWithAccounts {

    private Long id;
    private String direction;
    private String name;
    private String lastname;
    private List<AccountResponse> accounts;
}
