package com.fiap.tech.Stock.mappers;

import com.fiap.tech.Stock.dtos.StockRequestDTO;
import com.fiap.tech.Stock.entities.Stock;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class StockMapper {

    public static Stock stockDTOtoProduct(UUID id){
        Stock stock = new Stock();

        stock.setIdProduct(id);
        stock.setQuantity(0);
        stock.setUpdatedAt(LocalDateTime.now());

        return stock;
    }
}
