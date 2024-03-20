package com.fiap.tech.Gateway.controllers;

import com.fiap.tech.Gateway.dtos.CartAddRequestDTO;
import com.fiap.tech.Gateway.dtos.CartAddResponseDTO;
import com.fiap.tech.Gateway.dtos.CartCreateRequestDTO;
import com.fiap.tech.Gateway.dtos.CartCreateResponseDTO;
import com.fiap.tech.Gateway.services.CartGatewayService;
import jakarta.validation.Valid;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
public class CartGatewayController {

    private final CartGatewayService cartGatewayService;

    public CartGatewayController(CartGatewayService cartGatewayService) {
        this.cartGatewayService = cartGatewayService;
    }

    @GetMapping("/all-items/{id}")
    public ResponseEntity<Flux<CartAddResponseDTO>> getAllItemsOnCart(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(cartGatewayService.getAllItemsOnCart(id));
    }

    @PostMapping
    public ResponseEntity<Mono<CartCreateResponseDTO>> createCart(@RequestBody @Valid CartCreateRequestDTO cartCreateRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartGatewayService.createCart(cartCreateRequestDTO));
    }

    @PostMapping(path = "/add-item-cart/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<CartAddResponseDTO>> addItemCart(@PathVariable UUID id, @RequestBody @Valid CartAddRequestDTO cartAddRequestDTO) throws JSONException {
        Mono<CartAddResponseDTO> cartAddResponse = cartGatewayService.addItemCart(id, cartAddRequestDTO);

        JSONObject responseBody = new JSONObject();
        responseBody.put("message", "Item successfully added");

        return ResponseEntity.status(HttpStatus.CREATED).body(cartAddResponse);
    }
}
