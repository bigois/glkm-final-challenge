package com.fiap.tech.Gateway.controllers;

import com.fiap.tech.Gateway.dtos.*;
import com.fiap.tech.Gateway.services.CartGatewayService;
import com.fiap.tech.Gateway.services.ProductGatewayService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Mono<ProductResponseDTO>> getProductById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productGatewayService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Mono<ProductCreateResponseDTO>> createProduct(@Valid @RequestBody ProductCreateRequestDTO productCreateRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(productGatewayService.createProduct(productCreateRequestDTO));
    }

    @PutMapping("/stock/add-stock-quantity/{id}")
    public ResponseEntity<Mono<String>> addQuantityStock(@PathVariable UUID id, @RequestParam String quantity) {
        return ResponseEntity.status(HttpStatus.OK).body(productGatewayService.addQuantityStock(id, quantity));
    }

    @PutMapping("/stock/remove-stock-quantity/{id}")
    public ResponseEntity<Mono<String>> removeQuantityStock(@PathVariable UUID id, @RequestParam String quantity) {
        return ResponseEntity.status(HttpStatus.OK).body(productGatewayService.removeQuantityStock(id, quantity));
    }
}
