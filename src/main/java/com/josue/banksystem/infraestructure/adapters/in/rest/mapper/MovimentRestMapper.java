package com.josue.banksystem.infraestructure.adapters.in.rest.mapper;

import com.josue.banksystem.domain.model.Moviment;
import com.josue.banksystem.infraestructure.adapters.in.rest.response.moviment.MovimentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovimentRestMapper {

    MovimentResponse toMovimentResponse(Moviment moviment);
}
