package com.josue.banksystem.infraestructure.adapters.in.rest.response.moviment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MovimentResponse {

    private Long id;
    private Double amount;
    private LocalDateTime date;
    private String reference;
    private String type;

}
