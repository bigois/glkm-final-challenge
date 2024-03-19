package br.com.fiap.paymentapi.domain.service;

import br.com.fiap.paymentapi.adapter.out.mysql.repository.AccountRepository;
import br.com.fiap.paymentapi.adapter.out.mysql.repository.TransactionRepository;
import br.com.fiap.paymentapi.domain.model.Account;
import br.com.fiap.paymentapi.domain.model.Transaction;
import br.com.fiap.paymentapi.infrastructure.dto.request.PaymentRequest;
import br.com.fiap.paymentapi.infrastructure.dto.response.TransactionResponse;
import br.com.fiap.paymentapi.infrastructure.mapper.TransactionMapper;
import br.com.fiap.paymentapi.port.in.PaymentInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentInput {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final TransactionMapper transactionMapper;

    @Override
    public ResponseEntity<TransactionResponse> transfer(PaymentRequest request) {
        Account originAcc = accountService.findByAccountAndAgency(request.getOriginAccountNumber(), request.getOriginAccountAgency());
        Account targetAcc = accountService.findByAccountAndAgency(request.getTargetAccountNumber(), request.getTargetAccountAgency());

        if (!accountService.hasBalance(request.getAmmount(),
                originAcc.getBalance())) {
            throw new RuntimeException("Not enough balance to make this transaction!");
        }

        originAcc.setBalance(originAcc.getBalance().subtract(request.getAmmount()));
        targetAcc.setBalance(targetAcc.getBalance().add(request.getAmmount()));

        accountRepository.save(originAcc);
        accountRepository.save(targetAcc);

        Transaction transaction = transactionMapper.requestToModel(request);
        transaction.setTargetAccountName(targetAcc.getAccountOwnerName());
        transaction.setStatus("Success");

        Transaction model = transactionRepository.save(transaction);

        TransactionResponse transactionResponse = transactionMapper.modelToResponse(model);

        return ResponseEntity.ok().body(transactionResponse);
    }
}
