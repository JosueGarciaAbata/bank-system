package com.josue.banksystem.infraestructure.adapters.out.persistence.role;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String name;

}
