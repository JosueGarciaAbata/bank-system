package com.josue.banksystem.infraestructure.adapters.out.persistence.client;

import com.josue.banksystem.domain.models.Client;
import com.josue.banksystem.application.out.ClientRepository;
import com.josue.banksystem.infraestructure.adapters.out.persistence.mappers.ClientPersistenceMapper;
import com.josue.banksystem.infraestructure.adapters.out.persistence.mappers.RolePersistenceMapper;
import com.josue.banksystem.infraestructure.adapters.out.persistence.mappers.UserPersistenceMapper;
import com.josue.banksystem.infraestructure.adapters.out.persistence.role.RoleEntity;
import com.josue.banksystem.infraestructure.adapters.out.persistence.user.UserEntity;

import com.josue.banksystem.infraestructure.adapters.out.persistence.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component()//value="clientRepositoryImp"
@RequiredArgsConstructor
public class ClientRepositoryImp implements ClientRepository {

    private final ClientJpaRepository jpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final ClientPersistenceMapper mapper;
    private final UserPersistenceMapper userMapper;
    private final RolePersistenceMapper roleMapper;

    @Override
    public List<Client> findAll() {

        List<ClientEntity> entities =  (List<ClientEntity>) jpaRepository.findAll();
        List<Client> clients = entities
                .stream()
                .map(mapper::toClient)
                .toList();

        return clients;
    }

    @Override
    public Optional<Client> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toClient);
    }

    @Override
    public Optional<Client> findIncludingDeleted(Long id) {
        return jpaRepository.findIncludingDeleted(id).map(mapper::toClient);
    }

    // although i sent the user in the client it was a detached entity (the connection to the database was closed)
    // so i have to search for it again to have a managed entity.
    @Override
    public Client save(Client client) {
        ClientEntity clientEntity= mapper.toClientEntity(client);
        // always is gonna exists...
        UserEntity userEntity = userJpaRepository.findById(client.getUser().getId()).orElseThrow();

        // if the user has new roles, is added to the database, else the middle table is not touched (users_rolers) (the entity manager is smart).
        List<RoleEntity> roleEntities = client.getUser().getRoles()
                .stream()
                .map(roleMapper::toRoleEntity)
                .toList();

        userEntity.setRoles(roleEntities);
        clientEntity.setUser(userEntity);

        return mapper.toClient(jpaRepository.save(clientEntity));
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void restore(Client client) {
        jpaRepository.save(mapper.toClientEntity(client));
    }

    @Override
    public Optional<Client> findByIdWithAccounts(Long id) {
        Optional<ClientEntity> entity = jpaRepository.findByIdWithAccounts(id);
        return  entity.map(mapper::toClientWithAccounts);
    }

}
