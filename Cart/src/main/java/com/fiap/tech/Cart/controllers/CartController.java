package com.fiap.tech.Cart.controllers;

import com.fiap.tech.Cart.dtos.CartRequestDTO;
import com.fiap.tech.Cart.entities.Cart;
import com.fiap.tech.Cart.services.CartService;
import jakarta.validation.Valid;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping
    public ResponseEntity<List<Cart>> getCarts() {
        List<Cart> listCarts = cartService.getCarts();

        return ResponseEntity.status(HttpStatus.OK).body(listCarts);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerCartList(@RequestBody @Valid CartRequestDTO cartRequestDTO) throws JSONException {
        Cart cart = cartService.createCart(cartRequestDTO);

        JSONObject response = new JSONObject();
        response.put("id", cart.getId());
        response.put("id_user", cart.getId_user());
        response.put("total", cart.getTotal());
        response.put("message", "cart successfully created");

        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }

}