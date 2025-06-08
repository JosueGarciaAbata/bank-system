package com.josue.banksystem.infraestructure.adapters.in.rest.mapper;

import com.josue.banksystem.domain.model.Client;
import com.josue.banksystem.infraestructure.adapters.in.rest.request.client.CreateClientRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.request.client.UpdateClientRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.response.client.ClientAndUserResponse;
import com.josue.banksystem.infraestructure.adapters.in.rest.response.client.ClientResponse;
import com.josue.banksystem.infraestructure.adapters.in.rest.response.client.ClientResponseWithAccounts;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = { UserRestMapper.class, AccountRestMapper.class })
public interface ClientRestMapper {

    Client toClient(CreateClientRequest request);
    Client toClient(UpdateClientRequest request);

    ClientAndUserResponse toClientAndUserResponse(Client client);
    ClientResponse toClientResponse(Client client);

    ClientResponseWithAccounts toClientResponseWithAccounts(Client client);

}


