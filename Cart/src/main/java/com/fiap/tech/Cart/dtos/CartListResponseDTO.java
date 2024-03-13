package com.fiap.tech.Cart.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record CartListResponseDTO (
    UUID idCart,
    UUID idProduct,
    int quantity
){
}
