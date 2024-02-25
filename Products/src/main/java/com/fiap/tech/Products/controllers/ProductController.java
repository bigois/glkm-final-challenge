package com.fiap.tech.Products.controllers;

import com.fiap.tech.Products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    /*@Autowired
    private ProductService productService;*/


    /*public ResponseEntity<String> registerProduct(@RequestBody )*/

}
