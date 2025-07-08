package com.josue.banksystem.application.in.client;

import com.josue.banksystem.domain.models.Client;

public interface GetClientWithAccounts {

    Client get(Long id);
}
