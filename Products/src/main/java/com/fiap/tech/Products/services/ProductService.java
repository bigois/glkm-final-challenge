package com.fiap.tech.Products.services;

import com.fiap.tech.Products.dtos.PriceUpdateRequestDTO;
import com.fiap.tech.Products.dtos.ProductRequestDTO;
import com.fiap.tech.Products.entities.Product;
import com.fiap.tech.Products.mappers.ProductMapper;
import com.fiap.tech.Products.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProductById(UUID id){
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("product not found"));
    }

    @Transactional
    public Product createProduct(ProductRequestDTO productRequestDTO){
        return productRepository.save(ProductMapper.productDTOtoProduct(productRequestDTO));
    }

    @Transactional
    public void removeProduct(UUID id){
        Product product = productRepository.findProductById(id);

        if(product == null){
            throw new EntityNotFoundException("product not exists");
        }

        productRepository.deleteById(id);
    }

    @Transactional
    public Product updatePrice(UUID id, PriceUpdateRequestDTO priceUpdateRequestDTO){
        Product product = productRepository.findProductById(id);
        if(product == null) {
            throw new RuntimeException("product not exists");
        }

        product.setPrice(priceUpdateRequestDTO.price());

        return productRepository.save(product);
    }
}
