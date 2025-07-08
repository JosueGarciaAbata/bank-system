package com.josue.banksystem.infraestructure.adapters.out.persistence.mappers;

import com.josue.banksystem.domain.models.Account;
import com.josue.banksystem.domain.models.Client;
import com.josue.banksystem.infraestructure.adapters.out.persistence.client.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = { UserPersistenceMapper.class})
public interface ClientPersistenceMapper {

    @Named("toClient")
    //aqui esta el error si no pongo esto @Mapping(target = "accounts", ignore=true) y es logico, ya que si no le decimos como hacer algo a map struct el genera implementaciones por defecto.
    @Mapping(target = "accounts", ignore = true)
    Client toClient(ClientEntity clientEntity);

    @Named("toClientEntity")
    ClientEntity toClientEntity(Client client);
    // but this account does not have the client again

    @Named("toClientWithAccounts")
    default Client toClientWithAccounts(ClientEntity clientEntity) {

        Client client = toClient(clientEntity);
        client.setAccounts(
                clientEntity.getAccounts()
                        .stream()
                        .map(ae -> new Account(ae.getId(), ae.getAccountNumber(), ae.getBalance(), ae.getType()))
                        .toList()
        );

        return client;
    }
}
