package br.com.fiap.paymentapi.domain.service;

import br.com.fiap.paymentapi.adapter.out.mysql.repository.AccountRepository;
import br.com.fiap.paymentapi.domain.model.Account;
import br.com.fiap.paymentapi.exception.AccountNotFoundException;
import br.com.fiap.paymentapi.infrastructure.dto.request.AccountMovimentRequest;
import br.com.fiap.paymentapi.infrastructure.dto.request.AccountRequest;
import br.com.fiap.paymentapi.infrastructure.mapper.impl.AccountMapperImpl;
import br.com.fiap.paymentapi.port.in.AccountInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService implements AccountInput {

    private final AccountRepository repository;
    private final AccountMapperImpl mapper;

    @Override
    public ResponseEntity<?> create(AccountRequest request) {

        if (isAccountCreated(request.getAccountNumber())) {
            throw new RuntimeException("Account already registred!");
        }
        Account account = mapper.requestToModel(request);
//        account.setAccountOwnerName(request.getAccountOwner().getName());
        return ResponseEntity.ok().body(repository.save(account));

    }

    @Override
    public ResponseEntity<?> findByAccount(String accountNumber) {

        Account accountByAccountNumber = repository.findAccountByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account number: '" + accountNumber + "' Not found!")
                );

        return ResponseEntity.ok().body(accountByAccountNumber);
    }

    @Override
    public Account findByAccountAndAgency(String account, String agency) {
        return repository.findAccountByAccountNumberAndAgency(account, agency)
                .orElseThrow(() -> new RuntimeException("Account not found!")
                );
    }

    @Override
    public void deposit(AccountMovimentRequest request) {
        Account acc = findByAccountAndAgency(request.getAccountNumber(), request.getAccountAgency());
        acc.setBalance(acc.getBalance().add(request.getAmmount()));
        repository.save(acc);

    }

    @Override
    public void withdraw(AccountMovimentRequest request) {
        Account acc = findByAccountAndAgency(request.getAccountNumber(), request.getAccountAgency());
        if (!hasBalance(request.getAmmount(), acc.getBalance())) {
            throw new RuntimeException("Not enough balance to withdraw.");
        }

        acc.setBalance(acc.getBalance().subtract(request.getAmmount()));
        repository.save(acc);

    }

    public Boolean isAccountCreated(String accountNumber) {
        return repository.findAccountByAccountNumber(accountNumber).isPresent();
    }

    public boolean hasBalance(BigDecimal amount, BigDecimal balance) {
        BigDecimal result = balance.subtract(amount);
        return result.compareTo(BigDecimal.ZERO) >= 0;
    }
}
