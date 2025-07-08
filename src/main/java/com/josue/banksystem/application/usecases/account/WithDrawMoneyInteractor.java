package com.josue.banksystem.application.usecases.account;

import com.josue.banksystem.application.common.annotations.UseCase;
import com.josue.banksystem.application.in.account.WithDrawMoney;
import com.josue.banksystem.application.in.services.RegisterNewMoviment;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.domain.exception.AccountNotFoundException;
import com.josue.banksystem.domain.model.Account;
import com.josue.banksystem.domain.model.MovimentType;

// Recordemos que los casos de uso no deben replicar la logica de validacion
// que ya reside en los modelo de dominio.
public class WithDrawMoneyInteractor implements WithDrawMoney {

    private final AccountRepository repository;
    private final RegisterNewMoviment registerNewMoviment;

    public WithDrawMoneyInteractor(AccountRepository repository, RegisterNewMoviment registerNewMoviment) {
        this.repository = repository;
        this.registerNewMoviment = registerNewMoviment;
    }

    @UseCase
    public Account execute(Long accountId, double amount) {
        Account account = repository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        account.withdraw(amount);

        // We need to register the moviment.
        registerNewMoviment.register(accountId, amount, MovimentType.WITHDRAWAL);

        return repository.save(account);
    }
}
