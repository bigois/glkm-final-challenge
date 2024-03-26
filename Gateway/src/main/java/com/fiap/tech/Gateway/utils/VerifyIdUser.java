package com.fiap.tech.Gateway.utils;


import org.springframework.http.HttpHeaders;
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

    public Mono<Boolean> verifyUserExists(UUID id, String token){
        return userClient.get()
                .uri("/users/verify-user/" + id)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(Boolean.class);
    }
}
