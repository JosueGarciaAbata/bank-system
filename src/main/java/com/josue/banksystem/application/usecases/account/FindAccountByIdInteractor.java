package com.josue.banksystem.application.usecases.account;

import com.josue.banksystem.application.common.annotations.UseCase;
import com.josue.banksystem.application.in.account.FindAccountById;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.domain.exception.AccountNotFoundException;
import com.josue.banksystem.domain.model.Account;

public class FindAccountByIdInteractor implements FindAccountById {

    private AccountRepository accountRepository;

    public FindAccountByIdInteractor(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found with id=" + id));
    }
}
