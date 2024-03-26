package com.fiap.tech.Gateway.dtos;

import java.math.BigDecimal;

public record PaymentResponseDTO(
        long transaction_id,
        String date,
        BigDecimal total_amount,
        String origin_account,
        String origin_agency,
        String target_account,
        String target_agency,
        String target_account_name,
        String status
) {}
