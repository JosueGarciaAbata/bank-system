package com.josue.banksystem.infraestructure.adapters.out.persistence.transfer;

import com.josue.banksystem.application.out.TransferRepository;
import com.josue.banksystem.domain.models.Transfer;
import com.josue.banksystem.infraestructure.adapters.out.persistence.mappers.TransferPersistencerMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class TransferRepositoryImp implements TransferRepository {

    private final TransferRepositoryJpa repositoryJpa;
    private final TransferPersistencerMapper mapper;

    @Override
    public List<Transfer> findAll() {

        List<TransferEntity> entities = (List<TransferEntity>) repositoryJpa.findAll();

        return entities
                .stream()
                .map(mapper::toTransfer)
                .toList();
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return repositoryJpa.findById(id).map(mapper::toTransfer);
    }

    @Override
    public Transfer save(Transfer transfer) {
        TransferEntity transferEntity = mapper.toTransferEntity(transfer);
        log.info("TransferEntity: {}", transferEntity);
        Transfer saved = mapper.toTransfer(repositoryJpa.save(transferEntity));
        return saved;
    }

    // not implemented yet
    @Override
    public void delete(Transfer transfer) {

    }
}
