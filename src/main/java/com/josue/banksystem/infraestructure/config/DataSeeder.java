package com.josue.banksystem.infraestructure.config;

import com.josue.banksystem.infraestructure.adapters.out.persistence.role.RoleEntity;
import com.josue.banksystem.infraestructure.adapters.out.persistence.role.RoleJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleJpaRepository roleJpaRepository;

    public  DataSeeder(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
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

            roleJpaRepository.saveAll(List.of(user, admin, employee));
        }


    }
}
