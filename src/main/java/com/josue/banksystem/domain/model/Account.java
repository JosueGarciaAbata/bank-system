package com.josue.banksystem.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private Long id;
    private String accountNumber;
    private Double balance;
    private AccountType type;
    private Client client;
    private List<Moviment> moviments;

    public Account() {
        this.moviments = new ArrayList<>();
    }

    public Account(Long id, String accountNumber, Double balance, AccountType type) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
    }

    public Account(Long id, String accountNumber, Double balance, Client client, AccountType type) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.client = client;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Moviment> getMoviments() {
        return moviments;
    }

    public void setMoviments(List<Moviment> moviments) {
        this.moviments = moviments;
    }

    public void substractBalance(Double amount) {
        this.balance -= amount;
    }

    public void addBalance(Double amount) {
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", type='" + type + '\'' +
                ", client=" + client +
                '}';
    }
}
