package com.fiap.tech.Gateway.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CartAddRequestDTO (
    @NotNull(message = "cannot be null")
    UUID idProduct,
    @NotNull(message = "cannot be null")
    BigDecimal price,
    @NotNull(message = "cannot be null")
    int quantity
){}
