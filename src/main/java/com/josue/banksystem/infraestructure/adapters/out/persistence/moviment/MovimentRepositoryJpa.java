package com.josue.banksystem.infraestructure.adapters.out.persistence.moviment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentRepositoryJpa extends CrudRepository<MovimentEntity, Long> {

    @Query(value = "select * from moviments m where m.accoutn_id = ?1", nativeQuery = true)
    List<MovimentEntity> findAllByAccountId(Long accountId);

}
