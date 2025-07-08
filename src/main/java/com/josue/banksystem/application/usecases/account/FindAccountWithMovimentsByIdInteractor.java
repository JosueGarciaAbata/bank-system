package com.josue.banksystem.application.usecases.account;

import com.josue.banksystem.application.in.account.FindAccountWithMovimentsById;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.domain.exception.AccountNotFoundException;
import com.josue.banksystem.domain.models.Account;

public class FindAccountWithMovimentsByIdInteractor implements FindAccountWithMovimentsById {

    private final AccountRepository accountRepository;

    public FindAccountWithMovimentsByIdInteractor(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findByIdWithMoviments(id).orElseThrow(()->new AccountNotFoundException("Account not found with id=" +id));
    }
}
