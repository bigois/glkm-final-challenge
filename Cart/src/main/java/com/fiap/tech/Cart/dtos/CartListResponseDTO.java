package com.fiap.tech.Cart.dtos;

import java.util.UUID;

public record CartListResponseDTO (
    UUID idCart,
    UUID idProduct,
    int quantity
){
}
