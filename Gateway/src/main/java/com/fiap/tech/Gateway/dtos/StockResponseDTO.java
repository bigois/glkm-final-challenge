package com.fiap.tech.Gateway.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record StockResponseDTO(
        String id,

        String idProduct,

        int quantity,

        LocalDateTime updatedAt
) {
}
