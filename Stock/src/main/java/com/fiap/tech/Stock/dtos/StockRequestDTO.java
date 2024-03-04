package com.fiap.tech.Stock.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record StockRequestDTO(
        UUID idProduct,

        int quantity,

        LocalDateTime updatedAt
) {
}
