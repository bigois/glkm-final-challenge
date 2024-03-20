package com.fiap.tech.Cart.services;


import com.fiap.tech.Cart.dtos.CartRequestDTO;
import com.fiap.tech.Cart.entities.Cart;
import com.fiap.tech.Cart.mappers.CartMapper;
import com.fiap.tech.Cart.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Transactional(readOnly = true)
    public List<Cart> getCarts(){return cartRepository.findAll();}
    @Transactional
    public Cart createCart(CartRequestDTO cartRequestDTO){
        return cartRepository.save(CartMapper.cartDTOtoCart(cartRequestDTO));
    }
}