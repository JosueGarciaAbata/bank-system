package com.josue.banksystem.application.usecases.account;

import com.josue.banksystem.application.common.annotations.UseCase;
import com.josue.banksystem.application.in.account.CreateAccount;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.domain.model.Account;

public class CreateAccountInteractor implements CreateAccount {

    private final AccountRepository accountRepository;

    public CreateAccountInteractor(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @UseCase
    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }
}
