package com.josue.banksystem.infraestructure.adapters.out.persistence.moviment;

import com.josue.banksystem.application.out.MovimentRepository;
import com.josue.banksystem.domain.models.Moviment;
import com.josue.banksystem.infraestructure.adapters.out.persistence.mappers.MovimentPersistenceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MovimentRepositoryImp implements MovimentRepository {

    private final MovimentRepositoryJpa repositoryJpa;
    private final MovimentPersistenceMapper mapper;

    @Override
    public List<Moviment> findAll() {
        List<MovimentEntity> entites = (List<MovimentEntity>) repositoryJpa.findAll();
        return entites
                .stream()
                .map(mapper::toMoviment)
                .toList();
    }

    @Override
    public Optional<Moviment> findById(Long id) {
        return repositoryJpa
                .findById(id)
                .map(mapper::toMoviment);
    }

    @Override
    public List<Moviment> findAllByAccountId(Long accountId) {
        return repositoryJpa
                .findAllByAccountId(accountId)
                .stream()
                .map(mapper::toMoviment)
                .toList();
    }

    @Override
    public Moviment save(Moviment moviment) {
        MovimentEntity toSave = mapper.toMovimentEntity(moviment);
        Moviment saved = mapper.toMoviment(repositoryJpa.save(toSave));
        return saved;
    }

    @Override
    public void delete(Moviment moviment) {
        //
    }

    @Override
    public void restoreAll(List<Moviment> moviments) {
        repositoryJpa.saveAll(moviments.stream().map(mapper::toMovimentEntity).toList());
    }
}
