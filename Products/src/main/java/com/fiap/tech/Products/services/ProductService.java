package com.fiap.tech.Products.services;

import com.fiap.tech.Products.dtos.ProductRequestDTO;
import com.fiap.tech.Products.entities.Product;
import com.fiap.tech.Products.mappers.ProductMapper;
import com.fiap.tech.Products.repositories.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    @Transactional
    public Product createProduct(ProductRequestDTO productRequestDTO){
        return productRepository.save(ProductMapper.productDTOtoProduct(productRequestDTO));
    }

}
