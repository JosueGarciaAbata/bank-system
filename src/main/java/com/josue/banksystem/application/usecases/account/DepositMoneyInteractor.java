package com.josue.banksystem.application.usecases.account;

import com.josue.banksystem.application.in.account.DepositMoney;
import com.josue.banksystem.application.in.services.RegisterNewMoviment;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.domain.exception.AccountNotFoundException;
import com.josue.banksystem.domain.model.Account;
import com.josue.banksystem.domain.model.MovimentType;

public class DepositMoneyInteractor implements DepositMoney {

    private final AccountRepository repository;
    private final RegisterNewMoviment moviment;

    public DepositMoneyInteractor(AccountRepository repository, RegisterNewMoviment moviment) {
        this.repository = repository;
        this.moviment = moviment;
    }

    @Override
    public Account deposit(Long accountId, double amount) {

        Account account = repository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found, ID: " + accountId));

        account.deposit(amount);
        moviment.register(accountId, amount, MovimentType.DEPOSIT);
        return repository.save(account);
    }
}
