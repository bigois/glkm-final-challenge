package com.fiap.tech.Gateway.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record PaymentResponseDTO(
        long transactionId,
        String date,
        BigDecimal totalAmount,
        String originAccount,
        String originAgency,
        String targetAccount,
        String targetAgency,
        String targetAccountName,
        String status
) {}
