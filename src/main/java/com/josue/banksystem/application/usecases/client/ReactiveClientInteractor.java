package com.josue.banksystem.application.usecases.client;

import com.josue.banksystem.application.common.annotations.UseCase;
import com.josue.banksystem.application.in.client.ReactiveClient;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.application.out.ClientRepository;
import com.josue.banksystem.application.out.MovimentRepository;
import com.josue.banksystem.application.out.UserRepository;
import com.josue.banksystem.domain.exception.ClientNotFoundExcepcion;
import com.josue.banksystem.domain.exception.UserNotFoundException;
import com.josue.banksystem.domain.models.Account;
import com.josue.banksystem.domain.models.Client;
import com.josue.banksystem.domain.models.Moviment;
import com.josue.banksystem.domain.models.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * This use case is not working yet.
 */
public class ReactiveClientInteractor implements ReactiveClient {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final MovimentRepository movimentRepository;

    public ReactiveClientInteractor(ClientRepository clientRepository,
                                    UserRepository userRepository,
                                    AccountRepository accountRepository,
                                    MovimentRepository movimentRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.movimentRepository = movimentRepository;
    }

    @Override
    @UseCase
    public void reactive(Long id) {

        Client client = clientRepository.findIncludingDeleted(id)
                .orElseThrow(() -> new ClientNotFoundExcepcion("Client not found with id=" + id));

        Long userId = client.getUser().getId();
        Long clientId = client.getId();

        client.setUser(null);
        client.setAccounts(null);
        client.setDeletedAt(null);

        User user = userRepository.findIncludingDeleted(userId)
                        .orElseThrow(() -> new UserNotFoundException("User not found with id=" + userId));
        user.setDeletedAt(null);

        // Now i have to do the same thing for the accounts of the client and moviments.
        List<List<Moviment>> moviments = new ArrayList<>();
        List<Account> accounts = accountRepository.findByClientId(clientId);
        accounts.forEach(account -> {
            account.setDeletedAt(null);
            List<Moviment> movimentList = movimentRepository
                    .findAllByAccountId(account.getId())
                    .stream()
                    .peek(moviment -> moviment.setDeletedAt(null)).toList();
            moviments.add(movimentList);
        });

        clientRepository.restore(client);
        userRepository.restore(user);
        accountRepository.restoreAll(accounts);
        moviments.forEach(movimentRepository::restoreAll);
    }
}
