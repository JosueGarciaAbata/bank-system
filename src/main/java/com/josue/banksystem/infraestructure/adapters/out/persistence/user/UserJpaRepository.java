package com.josue.banksystem.infraestructure.adapters.out.persistence.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserJpaRepository extends CrudRepository<UserEntity, Long> {

    @Query("select us from UserEntity us join fetch us.roles where us.email = :email")
    Optional<UserEntity> findByEmailWithRoles(@Param("email") String email);

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
}
