package com.josue.banksystem.infraestructure.config.usecases;

import com.josue.banksystem.application.in.client.*;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.application.out.ClientRepository;
import com.josue.banksystem.application.usecases.client.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientUserCases {

    @Bean
    public GetClients getClientsInteractor(ClientRepository clientRepository) {
        return new GetClientsInteractor(clientRepository);
    }

    @Bean
    public GetClientWithAccounts getClientWithAccountsInteractor(ClientRepository clientRepository) {
        return new GetClientWithAccountsInteractor(clientRepository);
    }

    @Bean
    public CreateClient createClientInteractor(ClientRepository clientRepository) {
        return new CreateClientInteractor(clientRepository);
    }

    @Bean
    public UpdateClient updateClientInteractor(ClientRepository clientRepository) {
        return new UpdateClientInteractor(clientRepository);
    }

    @Bean
    public DeleteClient deleteClientInteractor(ClientRepository clientRepository, AccountRepository accountRepository) {
        return new DeleteClientInteractor(clientRepository, accountRepository);
    }

    @Bean
    public FindClientById findClientByIdInteractor(ClientRepository clientRepository) {
        return new FindClientByIdInteractor(clientRepository);
    }

}
