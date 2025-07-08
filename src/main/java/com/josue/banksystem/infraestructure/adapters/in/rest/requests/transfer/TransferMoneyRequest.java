package com.josue.banksystem.infraestructure.adapters.in.rest.requests.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransferMoneyRequest {

    @NotNull(message = "You have not provide the id_source_account.")
    @JsonProperty("id_source_account")
    private Long idSourceAccount;

    @NotNull(message = "You have not provide the id_dest_account")
    @JsonProperty("id_dest_account")
    private Long idDestAccount;

    @NotNull(message = "You have not provide the amount to transfer.")
    private Double amount;

    @NotBlank(message = "Description must not be null and empty.")
    private String description;


}
