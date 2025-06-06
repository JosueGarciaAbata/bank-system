package com.josue.banksystem.application.out;

import com.josue.banksystem.domain.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    List<Account> findAll();
    Optional<Account> findById(Long id);
    Account save(Account account);
    Account update(Account account, Long id);
    void delete(Long id);
    Account findByName(String name);
    Optional<Account> findByIdWithMoviments(Long id);
    List<Account> findByClientId(Long id);
}
