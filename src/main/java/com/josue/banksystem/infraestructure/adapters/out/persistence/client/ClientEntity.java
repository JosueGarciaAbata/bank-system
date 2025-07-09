package com.josue.banksystem.infraestructure.adapters.out.persistence.client;

import com.josue.banksystem.infraestructure.adapters.out.persistence.account.AccountEntity;
import com.josue.banksystem.infraestructure.adapters.out.persistence.user.UserEntity;

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
@Table(name = "clients", schema = "public")
@SQLDelete(sql = "UPDATE clients SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String direction;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountEntity> accounts;

    @Column(nullable = true, name = "deleted_at")
    private LocalDateTime deletedAt;
}
