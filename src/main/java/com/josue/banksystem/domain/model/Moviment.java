package com.josue.banksystem.domain.model;

import java.time.LocalDateTime;

public class Moviment {

    private Long id;
    private Account account;
    private Double amount;
    private LocalDateTime date;
    private String type;
    private String reference;

    public Moviment() {
    }

    public Moviment(Account account, Double amount, String type) {
        this.account = account;
        this.amount = amount;
        this.type = type;
    }

    public Moviment(Long id, Account account, Double amount) {
        this.id = id;
        this.account = account;
        this.amount = amount;
    }

    public Moviment(Long id, Double amount, LocalDateTime date, String type, String reference) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.reference = reference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
