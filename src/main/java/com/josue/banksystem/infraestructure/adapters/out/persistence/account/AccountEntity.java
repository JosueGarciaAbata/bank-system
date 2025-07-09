package com.josue.banksystem.infraestructure.adapters.out.persistence.account;

import com.josue.banksystem.domain.enumerations.AccountType;
import com.josue.banksystem.infraestructure.adapters.out.persistence.client.ClientEntity;
import com.josue.banksystem.infraestructure.adapters.out.persistence.moviment.MovimentEntity;
import com.josue.banksystem.infraestructure.adapters.out.persistence.transfer.TransferEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts")
@SQLDelete(sql = "UPDATE accounts SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
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

    @ManyToOne()
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovimentEntity> moviments;

    @OneToMany(mappedBy = "sourceAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransferEntity> sourceMoviments;

    @OneToMany(mappedBy = "destinationAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransferEntity> destinationMoviments;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
