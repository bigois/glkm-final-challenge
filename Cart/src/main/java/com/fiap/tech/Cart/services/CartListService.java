package com.fiap.tech.Cart.services;

import com.fiap.tech.Cart.entities.Cart;
import com.fiap.tech.Cart.entities.CartList;
import com.fiap.tech.Cart.mappers.CartListMapper;
import com.fiap.tech.Cart.repositories.CartListRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CartListService {

    @Autowired
    private CartListRepository cartListRepository;

    @Transactional(readOnly = true)
    public CartList getByCartId(UUID id){
        return cartListRepository.findByIdCart(id);
    }

    @Transactional
    public CartList createCartList(UUID idCart, UUID idProduct, BigDecimal price, int quantity){
        if(cartListRepository.existsByIdCart(idCart)){
            throw new RuntimeException("cart list already exists");
        }

        if(quantity <= 0) throw new RuntimeException("quantity must be more than 0");

        return cartListRepository.save(CartListMapper.cartListDTOtoCartList(idCart, idProduct, price, quantity));
    }

    @Transactional
    public CartList addItemCartList(UUID idCart, UUID idProduct, BigDecimal price, int quantity){
        boolean cartList = cartListRepository.existsByIdCart(idCart);

        if(!cartList){
            throw new RuntimeException("cart not exists");
        }
        if(cartListRepository.existsByIdProduct(idProduct)) throw new RuntimeException("product already exists in Cart List");

        if(quantity <= 0) throw new RuntimeException("quantity must be more than 0");

        CartList newItem = new CartList();

        newItem.setIdCart(idCart);
        newItem.setIdProduct(idProduct);
        newItem.setPrice(price);
        newItem.setQuantity(quantity);

        return cartListRepository.save(newItem);
    }

    @Transactional
    public void removeItemCartList(UUID idCart, UUID idProduct){
        CartList cartList = cartListRepository.findByIdCartAndIdProduct(idCart, idProduct);


        if(cartList == null) {
            throw new RuntimeException("cart not exists");
        }
        if(!cartListRepository.existsByIdProduct(idProduct)) throw new RuntimeException("product not exists in this cart");


        cartListRepository.deleteByIdCartAndIdProduct(idCart, idProduct);
    }

    public CartList addQuantityItem(UUID idCart, UUID idProduct, String quantity){
        CartList cartList = cartListRepository.findByIdCartAndIdProduct(idCart, idProduct);
        int quantityAddItem = Integer.parseInt(quantity);

        if(quantityAddItem <= 0) throw new RuntimeException("quantity must be more than 0");

        cartList.setQuantity(cartList.getQuantity() + quantityAddItem);

        return cartListRepository.save(cartList);
    }

    @Transactional
    public CartList removeQuantityItem(UUID idCart, UUID idProduct, String quantity){
        CartList cartList = cartListRepository.findByIdCartAndIdProduct(idCart, idProduct);
        int quantityRemoveItem = Integer.parseInt(quantity);

        if(quantityRemoveItem <= 0) throw new RuntimeException("quantity must be more than 0");
        if(cartList.getQuantity() - quantityRemoveItem < 0) throw new RuntimeException("invalid quantity, actual quantity: " + cartList.getQuantity() + " requested quantity: " + quantityRemoveItem);

        cartList.setQuantity(cartList.getQuantity() - quantityRemoveItem);

        return cartListRepository.save(cartList);
    }

  /*  public void calculateTotalPrice(UUID idCart){
        cartListRepository.findAllByIdCart(idCart);
    }*/
}

