package com.fiap.tech.Cart.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record CartResponseDTO(
        UUID id,
        UUID id_user,
        BigDecimal total
) {
}
