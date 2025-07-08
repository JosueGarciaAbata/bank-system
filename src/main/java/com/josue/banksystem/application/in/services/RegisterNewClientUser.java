package com.josue.banksystem.application.in.services;

import com.josue.banksystem.domain.models.Client;
import com.josue.banksystem.domain.models.User;

public interface RegisterNewClientUser {

    Client execute(User user, Client client);

}
