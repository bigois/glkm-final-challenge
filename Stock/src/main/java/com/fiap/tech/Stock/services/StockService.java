package com.fiap.tech.Stock.services;

import com.fiap.tech.Stock.entities.Stock;
import com.fiap.tech.Stock.mappers.StockMapper;
import com.fiap.tech.Stock.repositories.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Transactional
    public Stock create(UUID id){
        if(stockRepository.existsByIdProduct(id)){
            throw new RuntimeException("product already exists in stock");
        }

        return stockRepository.save(StockMapper.stockDTOtoProduct(id));
    }

    @Transactional(readOnly = true)
    public Stock getByProductId(UUID id){
        return stockRepository.findByIdProduct(id);
    }

    @Transactional
    public Stock addQuantityStock(UUID id, String quantity){
        Stock stock = stockRepository.findByIdProduct(id);
        int quantityAddStock = Integer.parseInt(quantity);

        if(quantityAddStock <= 0) throw new RuntimeException("quantity must be more than 0");

        stock.setQuantity(stock.getQuantity() + quantityAddStock);
        stock.setUpdatedAt(LocalDateTime.now());

        return stockRepository.save(stock);
    }

    @Transactional
    public Stock removeQuantityStock(UUID id, String quantity){
        Stock stock = stockRepository.findByIdProduct(id);
        int quantityRemoveStock = Integer.parseInt(quantity);

        if(quantityRemoveStock <= 0) throw new RuntimeException("quantity must be more than 0");
        if(stock.getQuantity() == 0) throw new RuntimeException("product out of stock");
        if(stock.getQuantity() - quantityRemoveStock < 0) throw new RuntimeException("no stock for this quantity, actual quantity: " + stock.getQuantity() + " requested quantity: " + quantityRemoveStock);

        stock.setQuantity(stock.getQuantity() - quantityRemoveStock);
        stock.setUpdatedAt(LocalDateTime.now());

        return stockRepository.save(stock);
    }

    @Transactional
    public void removeStock(UUID id){
        Stock stock = stockRepository.findByIdProduct(id);

        if(stock == null){
            throw new EntityNotFoundException("stock not exists");
        }

        stockRepository.deleteByIdProduct(id);
    }
}
