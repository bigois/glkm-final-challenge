package com.fiap.tech.Gateway.dtos;

import jakarta.validation.constraints.NotBlank;

public record PaymentRequestDTO(
        @NotBlank(message = "must not be blank or null")
        String origin_account_number,
        @NotBlank(message = "must not be blank or null")
        String origin_account_agency
){}
