package com.fiap.tech.Gateway.services;

import com.fiap.tech.Gateway.dtos.*;
import com.fiap.tech.Gateway.utils.ProductUtil;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ProductGatewayService {
    private ProductUtil productUtil;
    private final WebClient productClient;
    private final WebClient stockClient;

    private static final String API_AUTHENTICATE = "http://localhost:8089/auth";

    public ProductGatewayService(WebClient.Builder webClientBuilder, ProductUtil productUtil) {
        this.productClient = webClientBuilder.baseUrl("http://localhost:8081").build();
        this.stockClient = webClientBuilder.baseUrl("http://localhost:8082").build();
        this.productUtil = productUtil;
    }

    public Mono<ProductResponseDTO> getProductById(UUID id){
        return productClient.get()
                .uri("/products/" + id)
                .retrieve()
                .bodyToMono(ProductResponseDTO.class);
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

    public Mono<ProductCreateResponseDTO> createProduct(ProductCreateRequestDTO productCreateRequestDTO){
        return productClient.post()
                .uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(productCreateRequestDTO))
                .retrieve()
                .bodyToMono(ProductCreateResponseDTO.class);
    }

    public Mono<String> addQuantityStock(UUID id, String quantity){
        return stockClient.put()
                .uri(builder -> builder
                        .path("/stocks/add-stock-quantity/" + id)
                        .queryParam("quantity", quantity)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> removeQuantityStock(UUID id, String quantity){
        return stockClient.put()
                .uri(builder -> builder
                        .path("/stocks/remove-stock-quantity/" + id)
                        .queryParam("quantity", quantity)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Boolean authenticate(String token)  {
        String url = new StringBuilder().append(API_AUTHENTICATE).append("/check-token").toString();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);

        return response.getStatusCode().is2xxSuccessful();
    }
}
