package com.fiap.tech.Products.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PriceUpdateRequestDTO(
    @Positive(message = "only positive values")
    BigDecimal price
){}
