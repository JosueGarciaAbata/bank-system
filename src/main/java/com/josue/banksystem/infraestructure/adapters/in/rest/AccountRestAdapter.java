package com.josue.banksystem.infraestructure.adapters.in.rest;

import com.josue.banksystem.application.in.account.*;
import com.josue.banksystem.application.in.client.FindClientById;
import com.josue.banksystem.application.in.transfer.TransferMoney;
import com.josue.banksystem.domain.models.Account;
import com.josue.banksystem.domain.models.Client;
import com.josue.banksystem.infraestructure.adapters.in.rest.mappers.AccountRestMapper;
import com.josue.banksystem.infraestructure.adapters.in.rest.requests.account.CreateAccountRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.requests.account.DepositRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.requests.account.WithdrawRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.requests.transfer.TransferMoneyRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.responses.account.AccountResponse;
import com.josue.banksystem.infraestructure.adapters.in.rest.responses.account.AccountResponseWithMoviments;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class AccountRestAdapter {

    private CreateAccount createAccount;
    private GetAccounts getAccounts;
    private FindAccountById findAccountById;
    private FindAccountWithMovimentsById findAccountWithMovimentsById;
    private WithDrawMoney withDrawMoney;
    private DepositMoney depositMoney;

    private FindClientById  findClientById;
    private TransferMoney transferMoney;
    private AccountRestMapper mapper;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/")
    public List<AccountResponse> getAll() {
        return getAccounts.getAll().stream().map(mapper::toResponse).toList();
    }

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody CreateAccountRequest request) {

        Client client = findClientById.findById((request.getClientId())); // detached
        Account account = mapper.toAccount(request);
        account.setClient(client);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(createAccount.create(account)));
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_ADMIN')")
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferMoneyRequest request) {
        transferMoney.transfer(request.getIdSourceAccount(), request.getIdDestAccount(), request.getAmount(), request.getDescription());

        return ResponseEntity.ok("Transfer completed sucessfully.");
    }

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable Long id) {
        return mapper.toResponse(findAccountById.findById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_CLIENT', 'ROLE_ADMIN')")
    @GetMapping("/{id}/moviments")
    public AccountResponseWithMoviments withMoviments(@PathVariable Long id) {
        return mapper.toAccountResponseWithMoviments(findAccountWithMovimentsById.findById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_ADMIN')")
    @PostMapping("/withdraw/{accountId}")
    public AccountResponse withdrawMoney(@PathVariable Long accountId,
                                         @RequestBody WithdrawRequest withdrawRequest) {
        Account ac = withDrawMoney.execute(accountId, withdrawRequest.getAmount());
        return mapper.toResponse(ac);
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_ADMIN')")
    @PostMapping("/deposit/{accountId}")
    public AccountResponse depositMoney(@PathVariable Long accountId,
                                         @RequestBody DepositRequest depositRequest) {
        Account ac = depositMoney.deposit(accountId, depositRequest.getAmount());
        return mapper.toResponse(ac);
    }
}
