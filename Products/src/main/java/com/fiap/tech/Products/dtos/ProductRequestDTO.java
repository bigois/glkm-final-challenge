package com.fiap.tech.Products.dtos;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
public record ProductRequestDTO (
    @NotNull(message = "cannot be null")
    String name,

    @NotNull(message = "cannot be null")
    @Positive(message = "only positive values")
    BigDecimal price,

    @NotNull(message = "cannot be null")
    String brand
) {
    }