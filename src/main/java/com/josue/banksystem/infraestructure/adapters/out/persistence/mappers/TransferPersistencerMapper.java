package com.josue.banksystem.infraestructure.adapters.out.persistence.mappers;

import com.josue.banksystem.domain.model.Transfer;
import com.josue.banksystem.infraestructure.adapters.out.persistence.transfer.TransferEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { AccountPersistenceMapper.class })
public interface TransferPersistencerMapper {

    TransferEntity toTransferEntity(Transfer transfer);
    Transfer toTransfer(TransferEntity transferEntity);
}
