package com.josue.banksystem.application.in.account;

import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.domain.model.Account;

public interface DepositMoney {

    /**
     * Deposits money into an account.
     *
     * @param accountId the ID of the account to deposit money into
     * @param amount    the amount of money to deposit
     * @return the updated account after the deposit
     */
    public Account deposit(Long accountId, double amount);

}
