package com.josue.banksystem.application.usecases.client;

import com.josue.banksystem.application.in.client.CreateClient;
import com.josue.banksystem.application.out.ClientRepository;
import com.josue.banksystem.domain.model.Client;
import com.josue.banksystem.infraestructure.common.UseCase;

public class CreateClientInteractor implements CreateClient {

    private final ClientRepository clientRepository;

    public CreateClientInteractor(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @UseCase
    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }
}
