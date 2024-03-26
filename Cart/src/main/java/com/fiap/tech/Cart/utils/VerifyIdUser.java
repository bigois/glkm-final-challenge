package com.fiap.tech.Cart.utils;


import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class VerifyIdUser {

    private final WebClient userClient;

    public VerifyIdUser(WebClient.Builder webClientBuilder) {
        this.userClient = webClientBuilder.baseUrl("http://localhost:8089").build();
    }

    public Mono<Boolean> verifyUserExists(UUID id){
        return userClient.get()
                .uri("/users/verify-user/" + id)
                .retrieve()
                .bodyToMono(Boolean.class);
    }
}
