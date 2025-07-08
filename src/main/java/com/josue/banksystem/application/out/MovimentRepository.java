package com.josue.banksystem.application.out;

import com.josue.banksystem.domain.models.Moviment;

import java.util.List;
import java.util.Optional;

public interface MovimentRepository {

    List<Moviment> findAll();
    Optional<Moviment> findById(Long id);
    Moviment save(Moviment moviment);
    void delete(Moviment moviment);

}
