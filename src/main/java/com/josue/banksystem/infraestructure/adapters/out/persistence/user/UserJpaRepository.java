package com.josue.banksystem.infraestructure.adapters.out.persistence.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserJpaRepository extends CrudRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM users WHERE id = :id", nativeQuery = true)
    Optional<UserEntity> findIncludingDeleted(@Param("id") Long id);

    @Query("select us from UserEntity us join fetch us.roles where us.email = :email")
    Optional<UserEntity> findByEmailWithRoles(@Param("email") String email);

    @Modifying
    @Query(value = "UPDATE users SET is_enabled = false WHERE id = :userId", nativeQuery = true)
    void disableById(@Param("userId") Long userId);

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
}
