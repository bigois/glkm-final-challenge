package com.fiap.tech.Gateway.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Component
public class PaymentUtil {
    private final WebClient paymentClient;

    public PaymentUtil(WebClient.Builder webClientBuilder) {
        this.paymentClient = webClientBuilder.baseUrl("http://localhost:8084").build();
    }

    public void paymentCart(UUID id){

    }
}
