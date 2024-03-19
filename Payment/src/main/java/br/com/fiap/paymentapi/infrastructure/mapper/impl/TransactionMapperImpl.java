package br.com.fiap.paymentapi.infrastructure.mapper.impl;

import br.com.fiap.paymentapi.domain.model.Transaction;
import br.com.fiap.paymentapi.infrastructure.dto.request.PaymentRequest;
import br.com.fiap.paymentapi.infrastructure.dto.response.TransactionResponse;
import br.com.fiap.paymentapi.infrastructure.mapper.TransactionMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapperImpl implements TransactionMapper {
    @Override
    public TransactionResponse modelToResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getDate(),
                transaction.getTotalAmmount(),
                transaction.getOriginAccount(),
                transaction.getOriginAgency(),
                transaction.getTargetAccount(),
                transaction.getTargetAgency(),
                transaction.getTargetAccountName(),
                transaction.getStatus()
        );
    }

    @Override
    public Transaction requestToModel(PaymentRequest request) {
        return new Transaction(request.getAmmount(),
                request.getOriginAccountNumber(),
                request.getOriginAccountAgency(),
                request.getTargetAccountNumber(),
                request.getTargetAccountAgency());
    }
}
