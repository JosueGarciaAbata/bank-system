package com.josue.banksystem.application.usecases.account;

import com.josue.banksystem.application.common.annotations.UseCase;
import com.josue.banksystem.application.in.account.CreateAccount;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.domain.models.Account;

public class CreateAccountInteractor implements CreateAccount {

    private final AccountRepository accountRepository;

    public CreateAccountInteractor(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @UseCase
    @Override
    public Account create(Account account) {


        String dni = account.getClient().getDni(); // ej: "1500893685"

        // Prefijo: 00 + provincia (primeros 2 dígitos)
        String prefix = "00" + dni.substring(0, 2); // "0015"

        // Resto de la cédula, por ejemplo invirtiendo los últimos 8 dígitos
        String core = new StringBuilder(dni.substring(2)).reverse().toString(); // por ejemplo: "00893685" → "58639800"

        String finalAccountNumber = (prefix + core).substring(0, 10); // Truncamos si es muy largo

        account.setAccountNumber(finalAccountNumber);

        return accountRepository.save(account);
    }
}
