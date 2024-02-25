package com.fiap.tech.Products.repositories;

import com.fiap.tech.Products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
