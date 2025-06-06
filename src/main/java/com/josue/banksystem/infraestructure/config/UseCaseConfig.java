package com.josue.banksystem.infraestructure.config;

import com.josue.banksystem.application.in.account.CreateAccount;
import com.josue.banksystem.application.in.account.FindAccountById;
import com.josue.banksystem.application.in.account.FindAccountWithMovimentsById;
import com.josue.banksystem.application.in.account.GetAccounts;
import com.josue.banksystem.application.in.client.*;
import com.josue.banksystem.application.in.role.FindByNameRole;
import com.josue.banksystem.application.in.services.RegisterNewMoviment;
import com.josue.banksystem.application.in.transfer.RegisterNewTransfer;
import com.josue.banksystem.application.in.transfer.TransferMoney;
import com.josue.banksystem.application.in.user.CreateUser;
import com.josue.banksystem.application.in.user.FindUserById;
import com.josue.banksystem.application.in.user.GetUsers;
import com.josue.banksystem.application.in.user.UpdateUser;
import com.josue.banksystem.application.out.*;
import com.josue.banksystem.application.usecases.account.CreateAccountInteractor;
import com.josue.banksystem.application.usecases.account.FindAccountByIdInteractor;
import com.josue.banksystem.application.usecases.account.FindAccountWithMovimentsByIdInteractor;
import com.josue.banksystem.application.usecases.account.GetAccountsInteractor;
import com.josue.banksystem.application.usecases.client.*;
import com.josue.banksystem.application.usecases.role.FindByNameRoleInteractor;
import com.josue.banksystem.application.usecases.services.RegisterNewMovimentInteractor;
import com.josue.banksystem.application.usecases.transfer.RegisterNewTransferInteractor;
import com.josue.banksystem.application.usecases.transfer.TransferMoneyInteractor;
import com.josue.banksystem.application.usecases.user.CreateUserInteractor;
import com.josue.banksystem.application.usecases.user.FindUserByIdInteractor;
import com.josue.banksystem.application.usecases.user.GetUsersInteractor;
import com.josue.banksystem.application.usecases.user.UpdateUserInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UseCaseConfig {

    @Bean
    public GetClients  getClientsInteractor(ClientRepository clientRepository) {
        return new GetClientsInteractor(clientRepository);
    }

    @Bean
    public GetUsers getUsersInteractor(UserRepository userRepository) {
        return new GetUsersInteractor(userRepository);
    }

    @Bean
    public GetAccounts getAccountsInteractor(AccountRepository accountRepository) {
        return new GetAccountsInteractor(accountRepository);
    }

    @Bean
    public GetClientWithAccounts getClientWithAccountsInteractor(ClientRepository clientRepository) {
        return new GetClientWithAccountsInteractor(clientRepository);
    }

    @Bean
    public CreateClient createClientInteractor(ClientRepository clientRepository) {
        return new CreateClientInteractor(clientRepository);
    }

    @Bean
    public CreateUser createUserInteractor(UserRepository userRepository, RoleRepository roleRepository) {
        return new CreateUserInteractor(userRepository, roleRepository);
    }

    @Bean
    public CreateAccount createAccountInteractor(AccountRepository accountRepository) {
        return new CreateAccountInteractor(accountRepository);
    }

    @Bean
    public FindClientById findClientByIdInteractor(ClientRepository clientRepository) {
        return new FindClientByIdInteractor(clientRepository);
    }

    @Bean
    public FindByNameRole findByNameRoleInteractor(RoleRepository roleRepository) {
        return new FindByNameRoleInteractor(roleRepository);
    }

    @Bean
    public FindUserById findUserByIdInteractor(UserRepository userRepository) {
        return new FindUserByIdInteractor(userRepository);
    }

    @Bean
    public FindAccountById findAccountByIdInteractor(AccountRepository accountRepository) {
        return new FindAccountByIdInteractor(accountRepository);
    }

    @Bean
    public FindAccountWithMovimentsById  findAccountWithMovimentsByIdInteractor(AccountRepository accountRepository) {
        return new FindAccountWithMovimentsByIdInteractor(accountRepository);
    }

    @Bean
    public UpdateClient updateClientInteractor(ClientRepository clientRepository) {
        return new UpdateClientInteractor(clientRepository);
    }

    @Bean
    public UpdateUser updateUserInteractor(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new UpdateUserInteractor(userRepository, passwordEncoder);
    }

    @Bean
    public DeleteClient deleteClientInteractor(ClientRepository clientRepository, AccountRepository accountRepository) {
        return new DeleteClientInteractor(clientRepository, accountRepository);
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
}
