package com.josue.banksystem.infraestructure.adapters.out.persistence.client;

import com.josue.banksystem.infraestructure.adapters.out.persistence.account.AccountEntity;
import com.josue.banksystem.infraestructure.adapters.out.persistence.user.UserEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clients", schema = "public")
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

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "client")
    private List<AccountEntity> accounts;

}
