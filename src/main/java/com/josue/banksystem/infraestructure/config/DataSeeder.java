package com.josue.banksystem.infraestructure.config;

import com.josue.banksystem.application.out.ClientRepository;
import com.josue.banksystem.domain.models.Client;
import com.josue.banksystem.infraestructure.adapters.out.persistence.client.ClientEntity;
import com.josue.banksystem.infraestructure.adapters.out.persistence.client.ClientJpaRepository;
import com.josue.banksystem.infraestructure.adapters.out.persistence.role.RoleEntity;
import com.josue.banksystem.infraestructure.adapters.out.persistence.role.RoleJpaRepository;
import com.josue.banksystem.infraestructure.adapters.out.persistence.user.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleJpaRepository roleJpaRepository;
    private final ClientJpaRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private String email = "josuegarcab2@hotmail.com";
    private String password = "1234567890";

    public  DataSeeder(RoleJpaRepository roleJpaRepository,
                       ClientJpaRepository clientRepository,
                       PasswordEncoder passwordEncoder) {
        this.roleJpaRepository = roleJpaRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (roleJpaRepository.count() == 0) {
            RoleEntity user = new RoleEntity();
            user.setName("ROLE_USER");

            RoleEntity admin = new  RoleEntity();
            admin.setName("ROLE_ADMIN");

            RoleEntity employee = new RoleEntity();
            employee.setName("ROLE_EMPLOYEE");

            RoleEntity client = new RoleEntity();
            client.setName("ROLE_CLIENT");

            roleJpaRepository.saveAll(List.of(user, admin, employee, client));
        }

        if (clientRepository.count() == 0) {

            ClientEntity client = new ClientEntity();
            client.setName("Josue");
            client.setLastname("Garcia");
            client.setDni("1500903685");
            client.setDirection("Ambato");

            UserEntity user = new UserEntity();
            user.setEnabled(true);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));

            RoleEntity admin = roleJpaRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Role not found: ROLE_ADMIN"));

            client.setUser(user);
            user.setRoles(List.of(admin));
            clientRepository.save(client);
        }
    }
}
