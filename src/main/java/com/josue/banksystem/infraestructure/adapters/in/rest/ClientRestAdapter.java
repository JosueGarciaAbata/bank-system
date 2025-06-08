package com.josue.banksystem.infraestructure.adapters.in.rest;

import com.josue.banksystem.application.in.client.*;
import com.josue.banksystem.application.in.user.FindUserById;
import com.josue.banksystem.domain.model.Client;
import com.josue.banksystem.domain.model.User;
import com.josue.banksystem.infraestructure.adapters.in.rest.mapper.ClientRestMapper;
import com.josue.banksystem.infraestructure.adapters.in.rest.request.client.CreateClientRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.request.client.UpdateClientRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.response.client.ClientAndUserResponse;
import com.josue.banksystem.infraestructure.adapters.in.rest.response.client.ClientResponse;
import com.josue.banksystem.infraestructure.adapters.in.rest.response.client.ClientResponseWithAccounts;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@AllArgsConstructor
public class ClientRestAdapter {

    private final FindUserById findUserById;

    private CreateClient createClient;
    private GetClients getClients;
    private FindClientById findClientById;
    private UpdateClient updateClient;
    private DeleteClient deleteClient;
    private GetClientWithAccounts getClientWithAccounts;

    private ClientRestMapper clientMapper;

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @GetMapping("/")
    public List<ClientResponse> getAll() {
        return getClients.getAll()
                .stream()
                .map(client -> clientMapper.toClientResponse(client))
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ClientResponse getById(@PathVariable Long id) {
        return clientMapper.toClientResponse(findClientById.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/user")
    public ClientAndUserResponse getUserById(@PathVariable Long id) {
        Client client = findClientById.findById(id);
        return clientMapper.toClientAndUserResponse(client);
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    @GetMapping("/{id}/accounts")
    public ClientResponseWithAccounts getClientWithAccounts(@PathVariable Long id) {
        Client client = getClientWithAccounts.get(id);
        return clientMapper.toClientResponseWithAccounts(client);
    }

    @PreAuthorize("hasRole('ADMIN')")
    // i only can use this endpoint if i have a user.
    @PostMapping("/")
    public ClientResponse save(@Valid @RequestBody CreateClientRequest request) {
      User user = findUserById.findById(request.getUserId()); // detached
      Client client = clientMapper.toClient(request);
      client.setUser(user);
      return clientMapper.toClientResponse(createClient.create(client));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ClientResponse update(@PathVariable Long id,
                                        @Valid @RequestBody UpdateClientRequest request) {

        Client client = clientMapper.toClient(request);
        client.setDirection(request.getDirection());
        client.setName(request.getName());
        client.setLastname(request.getLastname());
        // who i want to update and the data to update.
        return clientMapper.toClientResponse(updateClient.update(id, client));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteClient.delete(id);
        return ResponseEntity.noContent().build();
    }

}
