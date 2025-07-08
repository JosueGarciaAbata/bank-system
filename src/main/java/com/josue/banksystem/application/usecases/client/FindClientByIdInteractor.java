package com.josue.banksystem.application.usecases.client;

import com.josue.banksystem.application.in.client.FindClientById;
import com.josue.banksystem.application.out.ClientRepository;
import com.josue.banksystem.domain.exception.ClientNotFoundExcepcion;
import com.josue.banksystem.domain.models.Client;

public class FindClientByIdInteractor implements FindClientById {

    private ClientRepository clientRepository;

    public FindClientByIdInteractor(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client findById(Long id) {
        Client client = clientRepository
                .findById(id)
                .orElseThrow(() -> new ClientNotFoundExcepcion("Client not found with id: " + id));

        return client;
    }
}
