package com.josue.banksystem.infraestructure.adapters.out.persistence.user;

import com.josue.banksystem.application.out.UserRepository;
import com.josue.banksystem.domain.model.User;
import com.josue.banksystem.infraestructure.adapters.out.persistence.mappers.UserPersistenceMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImp implements UserRepository {

    private final PasswordEncoder passwordEncoder;
    private final UserJpaRepository jpaRepository;
    private final UserPersistenceMapper mapper;

    @Override
    public List<User> findAll() {

        List<UserEntity> entities = (List<UserEntity>) jpaRepository.findAll();

        return entities
                .stream()
                .map(mapper::toUser)
                .toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toUser);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = mapper.toUserEntity(user);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.toUser(jpaRepository.save(userEntity));
    }

    // i think it's not necessary yet.
    @Override
    public User update(User user, Long id) {
        return null;
    }

    // not implemented yet (and never)
    @Override
    public void delete(Long id) {
        //
    }

    @Override
    public boolean existsByEmail(String email) {
       return jpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByEmailAndIdNot(String email, Long id) {
        return jpaRepository.existsByEmailAndIdNot(email, id);
    }
}
