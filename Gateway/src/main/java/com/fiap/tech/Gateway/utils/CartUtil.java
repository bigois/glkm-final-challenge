package com.fiap.tech.Gateway.utils;

import com.fiap.tech.Gateway.dtos.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class CartUtil {
    private final WebClient cartClient;

    public CartUtil(WebClient.Builder webClientBuilder) {
        this.cartClient = webClientBuilder.baseUrl("http://localhost:8083").build();
    }

    public Flux<CartAddResponseDTO> getAllItemsOnCart(UUID id) {
        return cartClient.get()
                .uri("/cartList/all-items/" + id)
                .retrieve()
                .bodyToFlux(CartAddResponseDTO.class);
    }

    public Mono<CartCreateResponseDTO> callCartCreateCart(CartCreateRequestDTO cartCreateRequestDTO) {
        return cartClient.post()
                .uri(builder -> builder
                        .path("/cartList/" + cartCreateRequestDTO.idCart())
                        .queryParam("idProduct", cartCreateRequestDTO.idProduct())
                        .queryParam("price", cartCreateRequestDTO.price())
                        .queryParam("quantity", cartCreateRequestDTO.quantity())
                        .build())
                .retrieve()
                .bodyToMono(CartCreateResponseDTO.class);
    }

    public Mono<CartAddResponseDTO> addItemCart(UUID idCart, CartAddRequestDTO cartAddRequestDTO) {
        return cartClient.post()
                .uri(builder -> builder
                        .path("/cartList/add-cartList-item/" + idCart)
                        .queryParam("idProduct", cartAddRequestDTO.idProduct())
                        .queryParam("price", cartAddRequestDTO.price())
                        .queryParam("quantity", cartAddRequestDTO.quantity())
                        .build())
                .retrieve()
                .bodyToMono(CartAddResponseDTO.class);
    }
}
