package com.fiap.tech.Gateway.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.tech.Gateway.dtos.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentGatwayService {
    private final WebClient paymentClient;
    private final ObjectMapper objectMapper;
    private final CartGatewayService cartGatewayService;

    public PaymentGatwayService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper, CartGatewayService cartGatewayService) {
        this.paymentClient = webClientBuilder.baseUrl("http://localhost:8084").build();
        this.objectMapper = objectMapper;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.cartGatewayService = cartGatewayService;
    }

    public Mono<AccountResponseDTO> getMainAccount(){
        return paymentClient.get()
                .uri("/accounts/" + "123321-4")
                .retrieve()
                .bodyToMono(AccountResponseDTO.class);
    }

    public Mono<AccountResponseDTO> createAccount(AccountRequestDTO accountRequestDTO) {
        return paymentClient.post()
                .uri("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(accountRequestDTO))
                .retrieve()
                .bodyToMono(String.class)
                .map(body -> {
                    try {
                        // Use o ObjectMapper para desserializar o JSON
                        return objectMapper.readValue(body, AccountResponseDTO.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return null;
                    }
                });
    }

    public Mono<PaymentResponseDTO> paymentCart(UUID id, PaymentRequestDTO paymentRequestDTO) {
        Mono<AccountResponseDTO> accountResponse = getMainAccount();
        Mono<CartCompleteResponseDTO> cartComplete = cartGatewayService.getAllItemsOnCart(id);

        if(cartComplete == null){
            throw new EntityNotFoundException("no items found on cart");
        }

        BigDecimal total = cartComplete.map(CartCompleteResponseDTO::getTotal).block();

        PaymentFormatterRequestDTO paymentFormatterRequestDTO = new PaymentFormatterRequestDTO(
                paymentRequestDTO.origin_account_number(),
                paymentRequestDTO.origin_account_agency(),
                accountResponse.map(AccountResponseDTO::accountNumber).block(),
                accountResponse.map(AccountResponseDTO::agency).block(),
                total
        );

        return paymentClient.post()
                .uri("/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(paymentFormatterRequestDTO))
                .retrieve()
                .bodyToMono(PaymentResponseDTO.class);
    }
}
