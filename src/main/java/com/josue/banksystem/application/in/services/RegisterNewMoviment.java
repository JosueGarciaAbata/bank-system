package com.josue.banksystem.application.in.services;

import com.josue.banksystem.domain.enumerations.MovimentType;

public interface RegisterNewMoviment {

    void register(Long idAccount, Double amount, MovimentType type);

}
