package com.josue.banksystem.infraestructure.config.usecases;

import com.josue.banksystem.application.in.account.CreateAccount;
import com.josue.banksystem.application.in.account.FindAccountById;
import com.josue.banksystem.application.in.account.FindAccountWithMovimentsById;
import com.josue.banksystem.application.in.account.GetAccounts;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.application.usecases.account.CreateAccountInteractor;
import com.josue.banksystem.application.usecases.account.FindAccountByIdInteractor;
import com.josue.banksystem.application.usecases.account.FindAccountWithMovimentsByIdInteractor;
import com.josue.banksystem.application.usecases.account.GetAccountsInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountUseCases {

    @Bean
    public GetAccounts getAccountsInteractor(AccountRepository accountRepository) {
        return new GetAccountsInteractor(accountRepository);
    }

    @Bean
    public CreateAccount createAccountInteractor(AccountRepository accountRepository) {
        return new CreateAccountInteractor(accountRepository);
    }

    @Bean
    public FindAccountById findAccountByIdInteractor(AccountRepository accountRepository) {
        return new FindAccountByIdInteractor(accountRepository);
    }

    @Bean
    public FindAccountWithMovimentsById findAccountWithMovimentsByIdInteractor(AccountRepository accountRepository) {
        return new FindAccountWithMovimentsByIdInteractor(accountRepository);
    }

}
