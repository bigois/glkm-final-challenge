package com.fiap.tech.Products.services;

import com.fiap.tech.Products.dtos.ProductRequestDTO;
import com.fiap.tech.Products.entities.Product;
import com.fiap.tech.Products.mappers.ProductMapper;
import com.fiap.tech.Products.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Transactional
    public Product createProduct(ProductRequestDTO productRequestDTO){
        return productRepository.save(ProductMapper.productDTOtoProduct(productRequestDTO));
    }

}
