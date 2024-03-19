package br.com.fiap.paymentapi.port.in;

import br.com.fiap.paymentapi.domain.model.Account;
import br.com.fiap.paymentapi.infrastructure.dto.request.AccountMovimentRequest;
import br.com.fiap.paymentapi.infrastructure.dto.request.AccountRequest;
import org.springframework.http.ResponseEntity;

public interface AccountInput {

    ResponseEntity<?> create(AccountRequest request);

    ResponseEntity<?> findByAccount(String account);

    Account findByAccountAndAgency(String account, String agency);

    void deposit(AccountMovimentRequest request);

    void withdraw(AccountMovimentRequest request);
}
