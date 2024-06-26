package com.fiap.tech.Gateway.services;

import com.fiap.tech.Gateway.dtos.*;
import com.fiap.tech.Gateway.utils.VerifyIdUser;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class CartGatewayService {

    private final VerifyIdUser verifyIdUser;
    private final WebClient cartClient;

    public CartGatewayService(WebClient.Builder webClientBuilder, VerifyIdUser verifyIdUser) {
        this.cartClient = webClientBuilder.baseUrl("http://localhost:8083").build();
        this.verifyIdUser = verifyIdUser;
    }

    public Mono<CartCompleteResponseDTO> getAllItemsOnCart(UUID id, String token) {
        if(Boolean.FALSE.equals(verifyIdUser.verifyUserExists(id, token.substring("Bearer ".length())).block())){
            throw new EntityNotFoundException("user id not found");
        }

        return cartClient.get()
                .uri("/cartList/all-items/" + id)
                .retrieve()
                .bodyToFlux(CartAllResponseDTO.class)
                .collectList()
                .map(cartItems -> {
                    BigDecimal total = cartItems.stream()
                            .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    return new CartCompleteResponseDTO(cartItems, total);
                });
    }

    public Mono<CartCreateResponseDTO> createCart(CartCreateRequestDTO cartCreateRequestDTO, String token) {
        if(Boolean.FALSE.equals(verifyIdUser.verifyUserExists(cartCreateRequestDTO.idCart(), token.substring("Bearer ".length())).block())){
            throw new EntityNotFoundException("user id not found");
        }

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

    public Mono<CartAddResponseDTO> addQuantityItemCart(UUID idCart, CartQuantityRequestDTO cartQuantityRequestDTO) {
        return cartClient.put()
                .uri(builder -> builder
                        .path("/cartList/add-quantity-item/" + idCart)
                        .queryParam("idProduct", cartQuantityRequestDTO.idProduct())
                        .queryParam("quantity", cartQuantityRequestDTO.quantity())
                        .build())
                .retrieve()
                .bodyToMono(CartAddResponseDTO.class);
    }

    public Mono<String> removeItemCart(UUID idCart, UUID idProduct) {
        return cartClient.delete()
                .uri(builder -> builder
                        .path("/cartList/remove-cartList-item/" + idCart)
                        .queryParam("idProduct", idProduct)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<CartAddResponseDTO> removeQuantityItemCart(UUID idCart, CartQuantityRequestDTO cartQuantityRequestDTO) {
        return cartClient.put()
                .uri(builder -> builder
                        .path("/cartList/remove-quantity-item/" + idCart)
                        .queryParam("idProduct", cartQuantityRequestDTO.idProduct())
                        .queryParam("quantity", cartQuantityRequestDTO.quantity())
                        .build())
                .retrieve()
                .bodyToMono(CartAddResponseDTO.class);
    }

    public Mono<String> removeAllItemsCart(UUID idCart) {
        return cartClient.delete()
                .uri(builder -> builder
                        .path("/cartList/remove-cartList-all-item/" + idCart)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}
