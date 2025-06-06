package com.josue.banksystem.application.out;

import com.josue.banksystem.domain.model.Moviment;
import com.josue.banksystem.domain.model.Transfer;

import java.util.List;
import java.util.Optional;

public interface TransferRepository {

    List<Transfer> findAll();
    Optional<Transfer> findById(Long id);
    Transfer save(Transfer transfer);
    void delete(Transfer transfer);

}
