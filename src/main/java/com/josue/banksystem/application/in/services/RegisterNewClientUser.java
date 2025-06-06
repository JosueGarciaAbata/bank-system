package com.josue.banksystem.application.in.services;

import com.josue.banksystem.domain.model.Client;
import com.josue.banksystem.domain.model.User;

public interface RegisterNewClientUser {

    Client execute(User user, Client client);

}
