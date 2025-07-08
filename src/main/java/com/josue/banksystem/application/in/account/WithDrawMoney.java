package com.josue.banksystem.application.in.account;

import com.josue.banksystem.domain.models.Account;

public interface WithDrawMoney {

    public Account execute(Long accountId, double amount);
}
