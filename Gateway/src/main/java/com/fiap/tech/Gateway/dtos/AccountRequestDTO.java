package com.fiap.tech.Gateway.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountRequestDTO (
        @NotBlank(message = "Agency must not be blank")
        String agency,
        @NotBlank(message = "Account number must not be blank")
        String account_number,
        @NotBlank(message = "Account type must not be blank")
        String account_type,
        @NotBlank(message = "Bank name must not be blank")
        String bank_name,
        @Valid @NotNull(message = "Account owner must not be null")
        AccountOwnerDTO account_owner,
        BigDecimal balance
){
}
