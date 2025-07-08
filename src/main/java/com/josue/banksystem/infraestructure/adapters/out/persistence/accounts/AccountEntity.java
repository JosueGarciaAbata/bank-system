package com.josue.banksystem.infraestructure.adapters.out.persistence.accounts;

import com.josue.banksystem.domain.model.AccountType;
import com.josue.banksystem.infraestructure.adapters.out.persistence.client.ClientEntity;
import com.josue.banksystem.infraestructure.adapters.out.persistence.moviment.MovimentEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, name = "account_number", unique = true)
    private String accountNumber;

    @Column(nullable = false, name = "balance")
    private Double balance;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, nullable = false)
    private AccountType type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @OneToMany(mappedBy = "account")
    private List<MovimentEntity> moviments;

}
