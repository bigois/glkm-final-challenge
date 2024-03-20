package com.fiap.tech.Gateway.controllers;

import com.fiap.tech.Gateway.dtos.CartCreateRequestDTO;
import com.fiap.tech.Gateway.dtos.CartCreateResponseDTO;
import com.fiap.tech.Gateway.dtos.ProductResponseDTO;
import com.fiap.tech.Gateway.services.CartGatewayService;
import com.fiap.tech.Gateway.services.ProductGatewayService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import com.fiap.tech.Gateway.dtos.ProductListResponseDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductGatewayController {

    private final ProductGatewayService productGatewayService;

    private final CartGatewayService cartGatewayService;

    public ProductGatewayController(ProductGatewayService productGatewayService, CartGatewayService cartGatewayService) {
        this.productGatewayService = productGatewayService;
        this.cartGatewayService = cartGatewayService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<ProductListResponseDTO>> getProducts() {
        Flux<ProductListResponseDTO> productList = productGatewayService.getProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<ProductResponseDTO>> getProductById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productGatewayService.getProductById(id));
    }

    /*
    @PostMapping
    public ResponseEntity<String> purchaseProduct(){
        return null;
    }

    @PostMapping
    public ResponseEntity<String> addProductToCart(){
        return null;
    }

    @PostMapping
    public ResponseEntity<String> purchaseCart(){
        return null;
    }*/
}
