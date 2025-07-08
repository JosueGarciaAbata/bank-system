package com.josue.banksystem.infraestructure.adapters.out.persistence.client;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientJpaRepository extends CrudRepository<ClientEntity, Long> {

    @Query("select ce from ClientEntity ce join fetch ce.accounts where ce.id = :id")
    Optional<ClientEntity> findByIdWithAccounts(@Param("id") Long id);
}
