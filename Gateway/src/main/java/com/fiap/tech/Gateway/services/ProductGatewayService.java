package com.fiap.tech.Gateway.services;

import com.fiap.tech.Gateway.dtos.ProductListResponseDTO;
import com.fiap.tech.Gateway.dtos.ProductResponseDTO;
import com.fiap.tech.Gateway.dtos.StockResponseDTO;
import com.fiap.tech.Gateway.utils.ProductUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class ProductGatewayService {
    private ProductUtil productUtil;
    private final WebClient productClient;

    public ProductGatewayService(WebClient.Builder webClientBuilder, ProductUtil productUtil) {
        this.productClient = webClientBuilder.baseUrl("http://localhost:8081").build();
        this.productUtil = productUtil;
    }

    public Flux<ProductListResponseDTO> getProducts() {
        return productClient.get()
                    .uri("/products")
                    .retrieve()
                    .bodyToFlux(ProductResponseDTO.class)
                    .flatMap(product -> {
                        Mono<StockResponseDTO> stockMono = productUtil.callAdditionalApi(product.id());

                        return Mono.zip(Mono.just(product), stockMono)
                                .map(tuple -> {
                                    ProductResponseDTO productData = tuple.getT1();
                                    StockResponseDTO stockData = tuple.getT2();

                                    return productUtil.combineProductAndAdditionalData(productData, stockData);
                                });
                    });
    }
}
