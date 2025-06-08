package com.josue.banksystem.application.usecases.services;

import com.josue.banksystem.application.in.services.RegisterNewMoviment;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.application.out.MovimentRepository;
import com.josue.banksystem.domain.exception.AccountNotFoundException;
import com.josue.banksystem.domain.model.Account;
import com.josue.banksystem.domain.model.Moviment;
import com.josue.banksystem.domain.model.MovimentType;
import com.josue.banksystem.infraestructure.common.UseCase;

public class RegisterNewMovimentInteractor implements RegisterNewMoviment {

    private final MovimentRepository repository;
    private final AccountRepository accountRepository;

    public RegisterNewMovimentInteractor(MovimentRepository repository,  AccountRepository accountRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
    }

    @UseCase
    @Override
    public void register(Long idAccount, Double amount, MovimentType type) {

        Account account = accountRepository.findById(idAccount)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + idAccount));

        Moviment moviment = new  Moviment(account, amount, type);
        repository.save(moviment);
    }
}
