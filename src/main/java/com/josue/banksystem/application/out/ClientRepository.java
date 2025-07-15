package com.josue.banksystem.application.out;

import com.josue.banksystem.domain.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> findAll();
    Optional<Client> findById(Long id);
    Optional<Client> findIncludingDeleted(Long id);
    Client save(Client client);
    void delete(Long id);
    void restore(Client client);
    Optional<Client> findByIdWithAccounts(Long id);
}
