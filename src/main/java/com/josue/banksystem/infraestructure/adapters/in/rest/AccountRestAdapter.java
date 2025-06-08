package com.josue.banksystem.infraestructure.adapters.in.rest;

import com.josue.banksystem.application.in.account.CreateAccount;
import com.josue.banksystem.application.in.account.FindAccountById;
import com.josue.banksystem.application.in.account.FindAccountWithMovimentsById;
import com.josue.banksystem.application.in.account.GetAccounts;
import com.josue.banksystem.application.in.client.FindClientById;
import com.josue.banksystem.application.in.transfer.TransferMoney;
import com.josue.banksystem.domain.model.Account;
import com.josue.banksystem.domain.model.Client;
import com.josue.banksystem.infraestructure.adapters.in.rest.mapper.AccountRestMapper;
import com.josue.banksystem.infraestructure.adapters.in.rest.request.account.CreateAccountRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.request.transfer.TransferMoneyRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.response.account.AccountResponse;
import com.josue.banksystem.infraestructure.adapters.in.rest.response.account.AccountResponseWithMoviments;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class AccountRestAdapter {

    private CreateAccount createAccount;
    private GetAccounts getAccounts;
    private FindAccountById findAccountById;
    private FindAccountWithMovimentsById findAccountWithMovimentsById;

    private FindClientById  findClientById;
    private TransferMoney transferMoney;
    private AccountRestMapper mapper;

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @GetMapping("/")
    public List<AccountResponse> getAll() {
        return getAccounts.getAll().stream().map(mapper::toResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody CreateAccountRequest request) {

        Client client = findClientById.findById((request.getClientId())); // detached
        Account account = mapper.toAccount(request);
        account.setClient(client);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(createAccount.create(account)));
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferMoneyRequest request) {
        transferMoney.transfer(request.getIdSourceAccount(), request.getIdDestAccount(), request.getAmount(), request.getDescription());

        return ResponseEntity.ok("Transfer completed sucessfully.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable Long id) {
        return mapper.toResponse(findAccountById.findById(id));
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    @GetMapping("/{id}/moviments")
    public AccountResponseWithMoviments withMoviments(@PathVariable Long id) {
        return mapper.toAccountResponseWithMoviments(findAccountWithMovimentsById.findById(id));
    }

}
