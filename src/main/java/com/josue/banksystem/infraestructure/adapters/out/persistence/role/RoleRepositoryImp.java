package com.josue.banksystem.infraestructure.adapters.out.persistence.role;

import com.josue.banksystem.domain.model.Role;
import com.josue.banksystem.application.out.RoleRepository;

import com.josue.banksystem.infraestructure.adapters.out.persistence.mappers.RolePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleRepositoryImp implements RoleRepository {

    private final RoleJpaRepository jpaRepository;
    private final RolePersistenceMapper mapper;

    @Override
    public List<Role> findAll() {
        List<RoleEntity> entities = (List<RoleEntity>) jpaRepository.findAll();

        return entities.stream()
                .map(ent -> mapper.toRole(ent))
                .toList();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return jpaRepository.findById(id)
                .map(ent -> mapper.toRole(ent));
    }

    @Override
    public Role save(Role user) {
        return mapper.toRole(jpaRepository.save(mapper.toRoleEntity(user)));
    }

    // not implemented yet
    @Override
    public Role update(Role user, Long id) {
        return null;
    }

    // not implemented yet
    @Override
    public void delete(Long id) {
    }

    @Override
    public Optional<Role> findByName(String name) {
        return jpaRepository.findByName(name)
                .map(mapper::toRole);
    }

}
