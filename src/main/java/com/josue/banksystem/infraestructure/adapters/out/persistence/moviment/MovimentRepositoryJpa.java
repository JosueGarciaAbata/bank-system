package com.josue.banksystem.infraestructure.adapters.out.persistence.moviment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentRepositoryJpa extends CrudRepository<MovimentEntity, Long> {

}
