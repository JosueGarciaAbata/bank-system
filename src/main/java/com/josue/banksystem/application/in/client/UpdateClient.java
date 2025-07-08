package com.josue.banksystem.application.in.client;

import com.josue.banksystem.domain.models.Client;

public interface UpdateClient {

    // who i am to update and what i want to update.
    Client update(Long id, Client client);

}
