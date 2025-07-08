package com.josue.banksystem.infraestructure.adapters.in.rest.mappers;

import com.josue.banksystem.domain.models.Account;
import com.josue.banksystem.infraestructure.adapters.in.rest.requests.account.CreateAccountRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.responses.account.AccountResponse;
import com.josue.banksystem.infraestructure.adapters.in.rest.responses.account.AccountResponseWithMoviments;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = { MovimentRestMapper.class })
public interface AccountRestMapper {

    @Mapping(target = "client", ignore = true)
    Account toAccount(CreateAccountRequest request);

    @Mapping(target = "clientId", source = "client.id")
    AccountResponse toResponse(Account acount);

    @Mapping(target = "clientId", source = "client.id")
    AccountResponseWithMoviments toAccountResponseWithMoviments(Account acount);
}
