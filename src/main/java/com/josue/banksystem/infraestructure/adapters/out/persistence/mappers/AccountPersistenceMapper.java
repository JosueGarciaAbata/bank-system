package com.josue.banksystem.infraestructure.adapters.out.persistence.mappers;

import com.josue.banksystem.domain.models.Account;
import com.josue.banksystem.domain.models.Moviment;
import com.josue.banksystem.infraestructure.adapters.out.persistence.account.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

// aqui no considero el mapeo de moviment para evitar el bucle.
@Mapper(componentModel = "spring", uses = { ClientPersistenceMapper.class })
public interface AccountPersistenceMapper {

    @Named("toAccount")
    @Mapping(target = "moviments", ignore = true)
    @Mapping(target = "client", qualifiedByName = "toClient")
    Account toAccount(AccountEntity accountEntity);

    @Named("toAccountEntity")
    @Mapping(target = "moviments", ignore = true)
    @Mapping(target = "client", qualifiedByName = "toClientEntity")
    AccountEntity toAccountEntity(Account account);

    // this method does not consider the account of the moviment.
    @Named("toAccountWithMoviments")
    default Account toAccountWithMoviments(AccountEntity accountEntity) {
        Account account = toAccount(accountEntity); // this does not have the moviments.
        // im goint to map them.
        account.setMoviments(
                accountEntity
                        .getMoviments()
                        .stream()
                        .map(mov -> new Moviment(mov.getId(), mov.getAmount(), mov.getDate(), mov.getType(), mov.getReference()))
                        .toList()
        );

        return account;
    }

}
