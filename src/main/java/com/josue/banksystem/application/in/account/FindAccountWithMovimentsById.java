package com.josue.banksystem.application.in.account;

import com.josue.banksystem.domain.model.Account;

import java.util.List;

public interface FindAccountWithMovimentsById {

    Account findById(Long id);
}
