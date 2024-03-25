package com.fiap.tech.Products.repositories;

import com.fiap.tech.Products.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findAll(Pageable pageable);
    
    void deleteById (UUID id);

    Product findProductById(UUID id);
}
