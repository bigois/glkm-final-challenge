package br.com.fiap.paymentapi.infrastructure.mapper;

import br.com.fiap.paymentapi.domain.model.Transaction;
import br.com.fiap.paymentapi.infrastructure.dto.request.PaymentRequest;
import br.com.fiap.paymentapi.infrastructure.dto.response.TransactionResponse;

public interface TransactionMapper {

    TransactionResponse modelToResponse(Transaction transaction);
    Transaction requestToModel(PaymentRequest request);
}
