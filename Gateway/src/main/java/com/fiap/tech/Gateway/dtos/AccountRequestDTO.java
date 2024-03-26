package com.fiap.tech.Gateway.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AccountRequestDTO (
        @NotBlank(message = "must not be blank")
        String accountNumber,

        @NotBlank(message = "must not be blank")
        String accountAgency,

        @Positive(message = "invalid value")
        BigDecimal ammount
){
}
