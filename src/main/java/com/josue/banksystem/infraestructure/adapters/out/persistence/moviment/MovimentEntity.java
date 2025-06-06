package com.josue.banksystem.infraestructure.adapters.out.persistence.moviment;

import com.josue.banksystem.infraestructure.adapters.out.persistence.accounts.AccountEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "moviments")
public class MovimentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // it's dangerous to use cascade. If I delete the account of this entity, that account is deleted of the db.
    @ManyToOne()
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    private Double amount;

    private LocalDateTime date;

    private String type;

    @Column(nullable = true, length = 60)
    private String reference;

    @PrePersist
    public void prePersist() {
        if (this.date == null) {
            this.date = LocalDateTime.now();
        }

        if (this.reference == null) {
            this.reference = UUID.randomUUID().toString();
        }
    }
}
