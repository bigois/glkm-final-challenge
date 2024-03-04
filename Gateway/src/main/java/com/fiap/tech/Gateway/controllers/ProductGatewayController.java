package com.fiap.tech.Gateway.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fiap.tech.Gateway.dtos.ProductListResponseDTO;
import com.fiap.tech.Gateway.dtos.ProductResponseDTO;
import com.fiap.tech.Gateway.dtos.StockResponseDTO;
import com.fiap.tech.Gateway.services.ProductGatewayService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
