package com.josue.banksystem.application.usecases.client;

import com.josue.banksystem.application.common.annotations.UseCase;
import com.josue.banksystem.application.in.client.DeleteClient;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.application.out.ClientRepository;
import com.josue.banksystem.domain.exception.ClientNotFoundExcepcion;
import com.josue.banksystem.domain.exception.ClientWithAccountsAssociatedException;
import com.josue.banksystem.domain.exception.ClientWithUserAssociatedException;
import com.josue.banksystem.domain.model.Account;
import com.josue.banksystem.domain.model.Client;

import java.util.List;

public class DeleteClientInteractor implements DeleteClient {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    public DeleteClientInteractor(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    // remember: here the entities are detached.
    @Override
    @UseCase
    public void delete(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundExcepcion("Client not found with id=" + id));
        List<Account> accounts = accountRepository.findByClientId(client.getId());
        client.setAccounts(accounts);

        if (!client.getAccounts().isEmpty()) {
            throw new ClientWithAccountsAssociatedException("Client has account associated. It could not be elminated.");
        }

        if (client.getUser() != null) {
            throw new ClientWithUserAssociatedException("Client has user associated. It could not be elminated.");
        }

        clientRepository.delete(id);
    }
}
