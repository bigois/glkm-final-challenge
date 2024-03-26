package com.fiap.tech.Cart.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CartListRequestDTO(
        @NotNull
        UUID idCart,
        @NotNull(message = "cannot be null")
        UUID idProduct,
        @NotNull(message = "cannot be null")
        int quantity
) {
}
