package com.josue.banksystem.application.usecases.client;

import com.josue.banksystem.application.in.client.UpdateClient;
import com.josue.banksystem.application.out.ClientRepository;
import com.josue.banksystem.domain.exception.ClientNotFoundExcepcion;
import com.josue.banksystem.domain.model.Client;

public class UpdateClientInteractor implements UpdateClient {

    private final ClientRepository clientRepository;

    public UpdateClientInteractor(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client update(Long id, Client client) {
        Client toUpdate = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundExcepcion("Client not found with id=" + id));
        toUpdate.setName(client.getName());
        toUpdate.setLastname(client.getLastname());
        toUpdate.setDirection(client.getDirection());
        return clientRepository.save(toUpdate);
    }
}
