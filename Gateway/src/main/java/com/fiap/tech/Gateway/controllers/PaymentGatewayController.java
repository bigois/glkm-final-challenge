package com.fiap.tech.Gateway.controllers;

import com.fiap.tech.Gateway.dtos.*;
import com.fiap.tech.Gateway.services.PaymentGatwayService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentGatewayController {

    private final PaymentGatwayService paymentGatwayService;

    public PaymentGatewayController(PaymentGatwayService paymentGatwayService) {
        this.paymentGatwayService = paymentGatwayService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Mono<PaymentResponseDTO>> paymentCart(@PathVariable UUID id, @Valid @RequestBody PaymentRequestDTO paymentRequestDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        return ResponseEntity.status(HttpStatus.OK).body(paymentGatwayService.paymentCart(id, paymentRequestDTO, token));
    }

    @PostMapping("/account")
    public ResponseEntity<Mono<AccountResponseDTO>> createAccount(@Valid @RequestBody AccountRequestDTO accountRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentGatwayService.createAccount(accountRequestDTO));
    }

    @PostMapping("/account/deposit")
    public ResponseEntity<Mono<String>> createAccount(@Valid @RequestParam AccountDepositRequestDTO accountDepositRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(paymentGatwayService.accountDeposit(accountDepositRequestDTO));
    }
}
