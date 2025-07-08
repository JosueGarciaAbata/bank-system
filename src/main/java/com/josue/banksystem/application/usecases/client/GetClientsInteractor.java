package com.josue.banksystem.application.usecases.client;

import com.josue.banksystem.application.in.client.GetClients;
import com.josue.banksystem.application.out.ClientRepository;
import com.josue.banksystem.domain.models.Client;

import java.util.List;

public class GetClientsInteractor implements GetClients {

    private ClientRepository clientRepository;

    public  GetClientsInteractor(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }
}
