package com.fiap.tech.Gateway.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PaymentFormatterRequestDTO(
        @NotBlank(message = "must not be blank or null")
        String origin_account_number,
        @NotBlank(message = "must not be blank or null")
        String origin_account_agency,
        @NotBlank(message = "must not be blank or null")
        String target_account_number,
        @NotBlank(message = "must not be blank or null")
        String target_account_agency,
        @Positive(message = "invalid ammount")
        BigDecimal ammount
) {}
