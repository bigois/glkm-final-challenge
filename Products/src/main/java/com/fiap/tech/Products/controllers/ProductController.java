package com.fiap.tech.Products.controllers;

import com.fiap.tech.Products.dtos.ProductRequestDTO;
import com.fiap.tech.Products.entities.Product;
import com.fiap.tech.Products.services.ProductService;
import jakarta.validation.Valid;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private final ProductService productService;

    private final WebClient webClient;

    public ProductController(WebClient.Builder webClientBuilder, ProductService productService) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity< List<Product>> getProducts(){
        List<Product> listProducts = productService.getProducts();

        return ResponseEntity.status(HttpStatus.OK).body(listProducts);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> registerProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO){
        Product product = productService.createProduct(productRequestDTO);

        return webClient.post()
                .uri("/stocks/" + product.getId())
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    try {
                        JSONObject responseBody = new JSONObject();
                        responseBody.put("id", product.getId());
                        responseBody.put("message", "product successfully registered");
                        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody.toString());
                    } catch (JSONException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error creating product, please try again");
                    }
                });
    }
}
