package com.josue.banksystem.application.out;

import com.josue.banksystem.domain.model.Account;
import com.josue.banksystem.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> findAll();
    Optional<Client> findById(Long id);
    Client save(Client client);
    void delete(Long id);
    Optional<Client> findByIdWithAccounts(Long id);
}
