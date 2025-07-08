package com.josue.banksystem.application.usecases.transfer;

import com.josue.banksystem.application.common.annotations.UseCase;
import com.josue.banksystem.application.in.transfer.RegisterNewTransfer;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.application.out.TransferRepository;
import com.josue.banksystem.domain.exception.AccountNotFoundException;
import com.josue.banksystem.domain.models.Account;
import com.josue.banksystem.domain.models.Transfer;

public class RegisterNewTransferInteractor implements RegisterNewTransfer {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    public RegisterNewTransferInteractor(AccountRepository accountRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    @UseCase
    @Override
    public void register(Long idSourceAccount, Long idDestinationAccount, Double amount, String description) {

        Account sourceAccount = accountRepository
                .findById(idSourceAccount)
                .orElseThrow(() -> new AccountNotFoundException("Source Account not found with the id " + idSourceAccount));

        Account destinationAccount = accountRepository
                .findById(idDestinationAccount)
                .orElseThrow(() -> new AccountNotFoundException("Destination Account not found with the id " + idDestinationAccount));


        Transfer transfer = new Transfer();
        transfer.setSourceAccount(sourceAccount);
        transfer.setDestinationAccount(destinationAccount);
        transfer.setAmount(amount);
        transfer.setDescription(description);
        transferRepository.save(transfer);
    }
}
