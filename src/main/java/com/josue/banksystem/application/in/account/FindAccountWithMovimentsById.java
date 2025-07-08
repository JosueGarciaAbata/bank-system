package com.josue.banksystem.application.in.account;

import com.josue.banksystem.domain.models.Account;

public interface FindAccountWithMovimentsById {

    Account findById(Long id);
}
