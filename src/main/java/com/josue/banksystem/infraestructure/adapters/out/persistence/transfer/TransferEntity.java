package com.josue.banksystem.infraestructure.adapters.out.persistence.transfer;

import com.josue.banksystem.domain.model.Account;
import com.josue.banksystem.infraestructure.adapters.out.persistence.accounts.AccountEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
@Getter
@Setter
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "source_account_id",  nullable = false)
    private AccountEntity sourceAccount;

    @ManyToOne()
    @JoinColumn(name = "destination_account_id",   nullable = false)
    private AccountEntity destinationAccount;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String description;

    @PrePersist
    public void prePersist() {
        this.date = LocalDateTime.now();
    }
}
