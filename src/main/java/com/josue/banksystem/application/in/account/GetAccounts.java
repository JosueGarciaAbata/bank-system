package com.josue.banksystem.application.in.account;

import com.josue.banksystem.domain.models.Account;

import java.util.List;

public interface GetAccounts {

    List<Account> getAll();
}
