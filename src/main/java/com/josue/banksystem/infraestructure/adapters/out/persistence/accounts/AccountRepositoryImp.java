package com.josue.banksystem.infraestructure.adapters.out.persistence.accounts;

import com.josue.banksystem.domain.exception.ClientNotFoundExcepcion;
import com.josue.banksystem.domain.model.Account;
import com.josue.banksystem.application.out.AccountRepository;
import com.josue.banksystem.infraestructure.adapters.out.persistence.client.ClientEntity;
import com.josue.banksystem.infraestructure.adapters.out.persistence.client.ClientJpaRepository;
import com.josue.banksystem.infraestructure.adapters.out.persistence.mappers.AccountPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccountRepositoryImp implements AccountRepository {

    private final AccountRepositoryJpa repositoryJpa;
    private final ClientJpaRepository clientJpaRepository;
    private final AccountPersistenceMapper persistenceMapper;
    private final AccountPersistenceMapper accountPersistenceMapper;

    @Override
    public List<Account> findAll() {
        List<AccountEntity> entities = (List<AccountEntity>) repositoryJpa.findAll();

        return entities
                .stream()
                .map(persistenceMapper::toAccount)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Account> findById(Long id) {
        return repositoryJpa
                .findById(id)
                .map(persistenceMapper::toAccount);
    }

    @Override
    public Account save(Account account) {
        Long clientId = account.getClient().getId();

        ClientEntity clientEntity = clientJpaRepository
                .findById(clientId)
                .get(); // managed

        // Como le envio un managed no da detached exception.
        AccountEntity entity = persistenceMapper.toAccountEntity(account);
        AccountEntity saved = repositoryJpa.save(entity);

        return persistenceMapper.toAccount(saved);
    }

    @Override
    public Account update(Account account, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public Account findByName(String name) {
        return null;
    }

    @Override
    public Optional<Account> findByIdWithMoviments(Long id) {
        return repositoryJpa.findByIdWithMoviments(id).map(persistenceMapper::toAccountWithMoviments);
    }

    @Override
    public List<Account> findByClientId(Long id) {
        return repositoryJpa.findByClientId(id)
                .stream().map(accountPersistenceMapper::toAccount)
                .toList();
    }
}
