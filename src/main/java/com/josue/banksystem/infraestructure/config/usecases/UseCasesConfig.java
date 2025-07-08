package com.josue.banksystem.infraestructure.config.usecases;

import com.josue.banksystem.application.in.account.*;
import com.josue.banksystem.application.in.role.FindByNameRole;
import com.josue.banksystem.application.in.services.RegisterNewMoviment;
import com.josue.banksystem.application.in.transfer.RegisterNewTransfer;
import com.josue.banksystem.application.in.transfer.TransferMoney;
import com.josue.banksystem.application.out.*;
import com.josue.banksystem.application.usecases.account.*;
import com.josue.banksystem.application.usecases.role.FindByNameRoleInteractor;
import com.josue.banksystem.application.usecases.services.RegisterNewMovimentInteractor;
import com.josue.banksystem.application.usecases.transfer.RegisterNewTransferInteractor;
import com.josue.banksystem.application.usecases.transfer.TransferMoneyInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public FindByNameRole findByNameRoleInteractor(RoleRepository roleRepository) {
        return new FindByNameRoleInteractor(roleRepository);
    }

    @Bean
    public RegisterNewMoviment registerNewMovimentInteractor(MovimentRepository movimentRepository, AccountRepository accountRepository) {
        return new RegisterNewMovimentInteractor(movimentRepository, accountRepository);
    }

    @Bean
    public RegisterNewTransfer registerNewTransferInteractor(TransferRepository transferRepository, AccountRepository accountRepository) {
        return new RegisterNewTransferInteractor(accountRepository, transferRepository);
    }

    @Bean
    public TransferMoney  transferMoneyInteractor(AccountRepository accountRepository,
                                                  RegisterNewMoviment registerNewMoviment,
                                                  RegisterNewTransfer registerNewTransfer) {
        return new TransferMoneyInteractor(accountRepository, registerNewMoviment, registerNewTransfer);
    }

    @Bean
    public WithDrawMoney withDrawMoney(AccountRepository accountRepository, RegisterNewMoviment registerNewMoviment) {
        return new WithDrawMoneyInteractor(accountRepository, registerNewMoviment);
    }

    @Bean
    public DepositMoney depositMoney(AccountRepository accountRepository, RegisterNewMoviment registerNewMoviment) {
        return new DepositMoneyInteractor(accountRepository, registerNewMoviment);
    }
}
