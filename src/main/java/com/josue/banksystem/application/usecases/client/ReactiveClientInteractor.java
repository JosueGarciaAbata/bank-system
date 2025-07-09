package com.josue.banksystem.application.usecases.client;

import com.josue.banksystem.application.in.client.ReactiveClient;
import com.josue.banksystem.application.out.ClientRepository;
import com.josue.banksystem.domain.exception.ClientNotFoundExcepcion;
import com.josue.banksystem.domain.models.Client;

public class ReactiveClientInteractor implements ReactiveClient {

    private final ClientRepository clientRepository;

    public ReactiveClientInteractor(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void reactive(Long id) {

        Client client = clientRepository.findByIdWithAccounts(id)
                .orElseThrow(() -> new ClientNotFoundExcepcion("Client not found with id=" + id));

        client.se

        clientRepository.delete();

    }
}
