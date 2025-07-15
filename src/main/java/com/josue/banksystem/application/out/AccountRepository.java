package com.josue.banksystem.application.out;

import com.josue.banksystem.domain.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    List<Account> findAll();
    Optional<Account> findById(Long id);
    List<Account> findAccountsByCliendId(Long clientId);
    Account save(Account account);
    Account update(Account account, Long id);
    void delete(Long id);
    void restoreAll(List<Account> accounts);
    Account findByName(String name);
    Optional<Account> findByIdWithMoviments(Long id);
    List<Account> findByClientId(Long id);
}
