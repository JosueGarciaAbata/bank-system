package com.josue.banksystem.application.in.account;

import com.josue.banksystem.domain.model.Account;

import java.util.List;

public interface GetAccounts {

    List<Account> getAll();
}
