package com.josue.banksystem.infraestructure.adapters.out.persistence.transfer;

import com.josue.banksystem.infraestructure.adapters.out.persistence.account.AccountEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transfers")
@SQLDelete(sql = "UPDATE transfers SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
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

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
