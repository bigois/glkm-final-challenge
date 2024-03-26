package com.fiap.tech.Gateway.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record CartCreateResponseDTO (
    @NotNull(message = "cannot be null")
    UUID id,
    @NotNull(message = "cannot be null")
    UUID idCart,
    @NotNull(message = "cannot be null")
    UUID idProduct,
    @NotNull(message = "cannot be null")
    BigDecimal price,
    @Positive(message = "cannot be null")
    int quantity
){}
