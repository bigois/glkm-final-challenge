package com.fiap.tech.Cart.mappers;

import com.fiap.tech.Cart.dtos.CartRequestDTO;
import com.fiap.tech.Cart.dtos.CartResponseDTO;
import com.fiap.tech.Cart.entities.Cart;


public class CartMapper {

    public static Cart cartDTOtoCart(CartRequestDTO cartRequestDTO){
        Cart cart = new Cart();

        cart.setId_user(cartRequestDTO.id_user());
        cart.setTotal(cartRequestDTO.total());

        return cart;
    }

    public static CartResponseDTO cartToCartDTO(Cart cart){
        return new CartResponseDTO(cart.getId(), cart.getId_user(),cart.getTotal());
    }
}