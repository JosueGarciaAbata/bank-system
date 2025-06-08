package com.josue.banksystem.application.in.services;

import com.josue.banksystem.domain.model.MovimentType;

public interface RegisterNewMoviment {

    void register(Long idAccount, Double amount, MovimentType type);

}
