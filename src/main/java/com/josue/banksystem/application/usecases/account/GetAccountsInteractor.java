package com.josue.banksystem.application.usecases.account;

import com.josue.banksystem.application.in.account.GetAccounts;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.domain.models.Account;

import java.util.List;

public class GetAccountsInteractor implements GetAccounts {

    private final AccountRepository accountRepository;

    public GetAccountsInteractor(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }
}
