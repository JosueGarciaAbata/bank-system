package com.josue.banksystem.infraestructure.adapters.in.rest.mapper;

import com.josue.banksystem.domain.model.Account;
import com.josue.banksystem.infraestructure.adapters.in.rest.request.account.CreateAccountRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.response.account.AccountResponse;
import com.josue.banksystem.infraestructure.adapters.in.rest.response.account.AccountResponseWithMoviments;
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
