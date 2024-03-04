package com.fiap.tech.Products.mappers;

import com.fiap.tech.Products.dtos.ProductRequestDTO;
import com.fiap.tech.Products.dtos.ProductResponseDTO;
import com.fiap.tech.Products.entities.Product;

public abstract class ProductMapper {

    public static Product productDTOtoProduct(ProductRequestDTO productRequestDTO){
        Product product = new Product();

        product.setName(productRequestDTO.name());
        product.setPrice(productRequestDTO.price());
        product.setBrand(productRequestDTO.brand());

        return product;
    }

    public static ProductResponseDTO productToProductDTO(Product product){
        return new ProductResponseDTO(product.getId(), product.getName(), product.getPrice(), product.getBrand());
    }
}
