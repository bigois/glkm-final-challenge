package com.fiap.tech.Gateway.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartCompleteResponseDTO{

    private List<CartAllResponseDTO> cartItems;
    private BigDecimal total;

    public CartCompleteResponseDTO(List<CartAllResponseDTO> cartItems, BigDecimal total) {
        this.cartItems = cartItems;
        this.total = total;
    }
}
