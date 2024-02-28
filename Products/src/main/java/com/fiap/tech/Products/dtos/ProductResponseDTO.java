package com.fiap.tech.Products.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO (
        UUID id,
        String name,
        BigDecimal price,
        String brand
){
}
