package com.fiap.tech.Gateway.services;

import com.fiap.tech.Gateway.dtos.CartAddRequestDTO;
import com.fiap.tech.Gateway.dtos.CartAddResponseDTO;
import com.fiap.tech.Gateway.dtos.CartCreateRequestDTO;
import com.fiap.tech.Gateway.dtos.CartCreateResponseDTO;
import com.fiap.tech.Gateway.utils.CartUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CartGatewayService {
    private final CartUtil cartUtil;

    public CartGatewayService(CartUtil cartUtil) {
        this.cartUtil = cartUtil;
    }

    public Flux<CartAddResponseDTO> getAllItemsOnCart(UUID id){
        return cartUtil.getAllItemsOnCart(id);
    }

    public Mono<CartCreateResponseDTO> createCart(CartCreateRequestDTO cartCreateRequestDTO){
         return cartUtil.callCartCreateCart(cartCreateRequestDTO);
    }

    public Mono<CartAddResponseDTO> addItemCart(UUID id, CartAddRequestDTO cartAddRequestDTO){
        return cartUtil.addItemCart(id, cartAddRequestDTO);
    }
}
