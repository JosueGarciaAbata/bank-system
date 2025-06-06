package com.josue.banksystem.application.in.transfer;

public interface TransferMoney {

    void transfer(Long idSourceAccount, Long idDestinationAccount, Double amount, String description);

}
