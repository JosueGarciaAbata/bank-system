package com.josue.banksystem.application.usecases.client;

import com.josue.banksystem.application.in.client.GetClientWithAccounts;
import com.josue.banksystem.application.out.ClientRepository;
import com.josue.banksystem.domain.model.Client;

public class GetClientWithAccountsInteractor implements GetClientWithAccounts {

    private final ClientRepository clientRepository;

    public  GetClientWithAccountsInteractor(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Client get(Long id) {
        Client client = clientRepository.findByIdWithAccounts(id).orElseThrow();
        return client;
    }
}
