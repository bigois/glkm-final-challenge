package com.fiap.tech.Gateway.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.fiap.tech.Gateway.dtos.StockResponseDTO;
import com.fiap.tech.Gateway.dtos.ProductResponseDTO;
import com.fiap.tech.Gateway.dtos.ProductListResponseDTO;
import java.math.BigDecimal;

@Component
public class ProductUtil {
    private final WebClient stockClient;

    public ProductUtil(WebClient.Builder webClientBuilder) {
        this.stockClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    public Mono<StockResponseDTO> callAdditionalApi(String productId) {
        return stockClient.get()
                .uri("/stocks/" + productId)
                .retrieve()
                .bodyToMono(StockResponseDTO.class)
                .doOnError(error -> { // Handle error
                    System.err.println("Error calling stock local with productId " + productId + ": " + error.getMessage());
                });
    }

    public ProductListResponseDTO combineProductAndAdditionalData(ProductResponseDTO productData, StockResponseDTO stockData) {
        BigDecimal price = new BigDecimal(productData.price());
        return new ProductListResponseDTO(productData.id(), productData.name(), productData.brand(), price, stockData.quantity());
    }
}
