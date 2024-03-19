package br.com.fiap.paymentapi.port.in;

import br.com.fiap.paymentapi.infrastructure.dto.request.PaymentRequest;
import br.com.fiap.paymentapi.infrastructure.dto.response.TransactionResponse;
import org.springframework.http.ResponseEntity;

public interface PaymentInput {

    ResponseEntity<TransactionResponse> transfer(PaymentRequest request);
}
