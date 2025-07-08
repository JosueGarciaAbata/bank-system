package com.josue.banksystem.infraestructure.adapters.out.persistence.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.josue.banksystem.infraestructure.adapters.out.persistence.role.RoleEntity;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(name = "is_enabled", nullable = false)
    private boolean enabled; // Este es para seguridad

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id" }))
    private List<RoleEntity> roles;

    @PrePersist
    public void prePersist() {
        this.enabled = true;
    }

}
