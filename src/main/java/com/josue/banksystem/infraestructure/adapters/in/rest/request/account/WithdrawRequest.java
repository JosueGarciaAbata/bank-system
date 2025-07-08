package com.josue.banksystem.infraestructure.adapters.in.rest.request.account;

public class WithdrawRequest {

    private double amount;

    public WithdrawRequest() {

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
