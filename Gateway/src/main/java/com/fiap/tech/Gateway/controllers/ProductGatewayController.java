package com.fiap.tech.Gateway.controllers;

import com.fiap.tech.Gateway.services.ProductGatewayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import com.fiap.tech.Gateway.dtos.ProductListResponseDTO;

@RestController
@RequestMapping("/api/v1/products")
public class ProductGatewayController {

    private final ProductGatewayService productGatewayService;

    public ProductGatewayController(ProductGatewayService productGatewayService) {
        this.productGatewayService = productGatewayService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<ProductListResponseDTO>> getProducts() {
        Flux<ProductListResponseDTO> productList = productGatewayService.getProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<String> getProductById(){
        return null;
    }

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
