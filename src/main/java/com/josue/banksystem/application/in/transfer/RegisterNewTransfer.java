package com.josue.banksystem.application.in.transfer;

public interface RegisterNewTransfer {

    void register(Long idSourceAccount, Long idDestinationAccount, Double amount, String description);

}
