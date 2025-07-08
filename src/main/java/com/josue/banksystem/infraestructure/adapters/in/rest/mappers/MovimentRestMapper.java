package com.josue.banksystem.infraestructure.adapters.in.rest.mappers;

import com.josue.banksystem.domain.models.Moviment;
import com.josue.banksystem.infraestructure.adapters.in.rest.responses.moviment.MovimentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovimentRestMapper {

    MovimentResponse toMovimentResponse(Moviment moviment);
}
