package com.fiap.tech.Gateway.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductCreateRequestDTO (
        @NotNull(message = "cannot be null")
        String name,

        @NotNull(message = "cannot be null")
        @Positive(message = "only positive values")
        BigDecimal price,

        @NotNull(message = "cannot be null")
        String brand
){}
