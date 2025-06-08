package com.josue.banksystem.application.usecases.transfer;

import com.josue.banksystem.application.in.services.RegisterNewMoviment;
import com.josue.banksystem.application.in.transfer.RegisterNewTransfer;
import com.josue.banksystem.domain.exception.AccountNotFoundException;
import com.josue.banksystem.domain.exception.InsufficientAccountBalance;
import com.josue.banksystem.application.in.transfer.TransferMoney;
import com.josue.banksystem.domain.model.Account;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.domain.model.MovimentType;
import com.josue.banksystem.infraestructure.common.UseCase;


public class TransferMoneyInteractor implements TransferMoney {

    private final AccountRepository accountRepository;
    private final RegisterNewMoviment registerNewMoviment;
    private final RegisterNewTransfer registerNewTransfer;

    public TransferMoneyInteractor(
            AccountRepository accountRepository,
            RegisterNewMoviment registerNewMoviment,
            RegisterNewTransfer registerNewTransfer) {
        this.accountRepository = accountRepository;
        this.registerNewMoviment = registerNewMoviment;
        this.registerNewTransfer = registerNewTransfer;
    }

    @UseCase
    @Override
    public void transfer(Long idSourceAccount, Long idDestinationAccount, Double amount, String description) {

        Account sourceAccount = accountRepository.findById(idSourceAccount)
                .orElseThrow(() -> new AccountNotFoundException("Source account not found"));

        Account destinationAccount = accountRepository.findById(idDestinationAccount)
                .orElseThrow(() -> new AccountNotFoundException("Destination account not found"));

        // source account has money
        if (sourceAccount.getBalance() <= 0 || amount > sourceAccount.getBalance()) {
            throw  new InsufficientAccountBalance("Source account does not sufficient balance to transfer money.");
        }

        // subtract money from source
        sourceAccount.substractBalance(amount);

        // add money to destination
        destinationAccount.addBalance(amount);

        // do operations
        accountRepository.save(sourceAccount);
        // forzar exception to test
        // throw new RuntimeException("Transfer failed.");
        accountRepository.save(destinationAccount);

        // register transfer
        registerNewTransfer.register(idSourceAccount, idDestinationAccount, amount, description);

        // to register the moviment.
        registerNewMoviment.register(idSourceAccount, amount, MovimentType.WITHDRAWAL);
        registerNewMoviment.register(idDestinationAccount, amount,  MovimentType.DEPOSIT);
    }
}
