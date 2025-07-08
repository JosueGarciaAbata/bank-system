package com.josue.banksystem.infraestructure.adapters.out.persistence.mappers;

import com.josue.banksystem.domain.models.Moviment;
import com.josue.banksystem.infraestructure.adapters.out.persistence.moviment.MovimentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// aqui si considero el mapeo de las cuentas ya que al guardar un movimiento necesito saber de que  cuneta es.
@Mapper(componentModel = "spring", uses = { AccountPersistenceMapper.class })
public interface MovimentPersistenceMapper {

    @Mapping(target = "account", qualifiedByName = "toAccountEntity")
    MovimentEntity toMovimentEntity(Moviment moviment);

    @Mapping(target = "account", qualifiedByName = "toAccount")
    Moviment toMoviment(MovimentEntity entity);
}

