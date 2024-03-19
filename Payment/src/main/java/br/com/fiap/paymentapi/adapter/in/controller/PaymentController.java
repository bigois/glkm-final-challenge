package br.com.fiap.paymentapi.adapter.in.controller;

import br.com.fiap.paymentapi.domain.service.PaymentService;
import br.com.fiap.paymentapi.infrastructure.dto.request.PaymentRequest;
import br.com.fiap.paymentapi.infrastructure.dto.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/payments")
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<TransactionResponse> toPay(@RequestBody PaymentRequest request) {
        return service.transfer(request);
    }
}
