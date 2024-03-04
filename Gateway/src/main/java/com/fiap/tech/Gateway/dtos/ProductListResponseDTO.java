package com.fiap.tech.Gateway.dtos;

import java.math.BigDecimal;

public record ProductListResponseDTO(
        String idProduct,
        String name,
        String brand,
        BigDecimal price,
        int quantity
) {
}
