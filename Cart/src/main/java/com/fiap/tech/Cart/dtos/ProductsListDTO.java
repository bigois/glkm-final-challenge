package com.fiap.tech.Cart.dtos;

import java.util.UUID;

public record ProductsListDTO(
        UUID id,
        String quantity,
        String total
) {
}
