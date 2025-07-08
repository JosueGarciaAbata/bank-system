package com.josue.banksystem.infraestructure.adapters.out.persistence.account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepositoryJpa extends CrudRepository<AccountEntity, Long> {

    @Query("SELECT ae FROM AccountEntity ae LEFT JOIN ae.moviments WHERE ae.id = :id")
    Optional<AccountEntity> findByIdWithMoviments(@Param("id") Long id);

    @Query("select ae from AccountEntity ae where ae.client.id = :clientId")
    List<AccountEntity> findByClientId(@Param("clientId") Long clientId);
}
