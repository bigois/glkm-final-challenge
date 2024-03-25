package com.fiap.tech.Stock.repositories;

import com.fiap.tech.Stock.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID> {
    Stock findByIdProduct(UUID id);

    boolean existsByIdProduct(UUID id);

    void deleteByIdProduct (UUID id);
}
