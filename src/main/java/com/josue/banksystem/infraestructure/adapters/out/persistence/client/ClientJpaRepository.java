package com.josue.banksystem.infraestructure.adapters.out.persistence.client;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientJpaRepository extends CrudRepository<ClientEntity, Long> {

    // It's necessary to use native query to include deleted clients
    @Query(value = "SELECT * FROM clients WHERE id = :id", nativeQuery = true)
    Optional<ClientEntity> findIncludingDeleted(@Param("id") Long id);

    // This method is used to restore a client by setting the deletedAt field to null.
    // It's necessary to use @Transactional annotations because all queries from jpa are only read by default.
    //@Modifying
    //@Query("UPDATE ClientEntity c SET c.deletedAt = NULL WHERE c.id = :id")
    //void restoreById(@Param("id") Long id);

    @Query("select ce from ClientEntity ce join fetch ce.accounts where ce.id = :id")
    Optional<ClientEntity> findByIdWithAccounts(@Param("id") Long id);

}
