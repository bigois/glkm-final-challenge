package com.fiap.tech.Cart.mappers;

import com.fiap.tech.Cart.entities.CartList;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class CartListMapper {

    public static CartList cartListDTOtoCartList(UUID idCart, UUID idProduct, BigDecimal price, int quantity){
        CartList cartList = new CartList();

        cartList.setIdCart(idCart);
        cartList.setIdProduct(idProduct);
        cartList.setPrice(price);
        cartList.setQuantity(quantity);

        return cartList;
    }

}
