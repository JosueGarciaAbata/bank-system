package com.josue.banksystem.application.usecases.client;

import com.josue.banksystem.application.common.annotations.UseCase;
import com.josue.banksystem.application.in.client.DeleteClient;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.application.out.ClientRepository;
import com.josue.banksystem.application.out.UserRepository;
import com.josue.banksystem.domain.exception.ClientNotFoundExcepcion;
import com.josue.banksystem.domain.models.Client;
import com.josue.banksystem.domain.models.User;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

public class DeleteClientInteractor implements DeleteClient {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public DeleteClientInteractor(ClientRepository clientRepository,
                                  UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    // remember: here the entities are detached.
    @Override
    @UseCase
    // Creo que aqui deberia borrar las cuentas asociadas al cliente
    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundExcepcion("Client not found with id=" + id));

        userRepository.disableById(client.getUser().getId());

        client.setDeletedAt(LocalDateTime.now());
        clientRepository.delete(client);
    }
}
