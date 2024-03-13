package com.fiap.tech.Cart.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CartRequestDTO (
        @NotNull
        UUID id_user,
        @NotNull
        BigDecimal total

){
}
