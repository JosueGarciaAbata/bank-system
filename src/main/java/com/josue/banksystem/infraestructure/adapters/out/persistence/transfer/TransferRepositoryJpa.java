package com.josue.banksystem.infraestructure.adapters.out.persistence.transfer;

import org.springframework.data.repository.CrudRepository;

public interface TransferRepositoryJpa extends CrudRepository<TransferEntity, Long> {
}
