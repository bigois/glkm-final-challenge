package com.fiap.tech.Products.controllers;

import com.fiap.tech.Products.dtos.ProductRequestDTO;
import com.fiap.tech.Products.dtos.ProductResponseDTO;
import com.fiap.tech.Products.entities.Product;
import com.fiap.tech.Products.services.ProductService;
import jakarta.validation.Valid;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@RequestMapping(path = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {


    @Autowired
    private ProductService productService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO) throws JSONException {
        Product product = productService.createProduct(productRequestDTO);

        JSONObject response = new JSONObject();
        response.put("id", product.getId());
        response.put("message", "product successfully registered");

        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }

}
