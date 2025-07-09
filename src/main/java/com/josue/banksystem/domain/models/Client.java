package com.josue.banksystem.domain.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private Long id;
    private String direction;
    private String name;
    private String lastname;
    private User user;
    private List<Account> accounts;
    private LocalDateTime deletedAt;

    public Client() {
        this.accounts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", direction='" + direction + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", user=" + user +
                '}';
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
