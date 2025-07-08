package com.josue.banksystem.infraestructure.adapters.in.rest.mappers;

import com.josue.banksystem.domain.models.Client;
import com.josue.banksystem.infraestructure.adapters.in.rest.requests.client.CreateClientRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.requests.client.UpdateClientRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.responses.client.ClientAndUserResponse;
import com.josue.banksystem.infraestructure.adapters.in.rest.responses.client.ClientResponse;
import com.josue.banksystem.infraestructure.adapters.in.rest.responses.client.ClientResponseWithAccounts;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserRestMapper.class, AccountRestMapper.class })
public interface ClientRestMapper {

    Client toClient(CreateClientRequest request);
    Client toClient(UpdateClientRequest request);

    ClientAndUserResponse toClientAndUserResponse(Client client);
    ClientResponse toClientResponse(Client client);

    ClientResponseWithAccounts toClientResponseWithAccounts(Client client);

}


