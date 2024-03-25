package br.com.fiap.paymentapi.adapter.in.controller;

import br.com.fiap.paymentapi.domain.model.Account;
import br.com.fiap.paymentapi.domain.service.AccountService;
import br.com.fiap.paymentapi.infrastructure.dto.request.AccountMovimentRequest;
import br.com.fiap.paymentapi.infrastructure.dto.request.AccountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody AccountRequest request){
        Account account = service.create(request);
        return ResponseEntity.ok().body(account);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> findByAccount(@PathVariable String accountNumber){
        return ResponseEntity.ok().body(service.findByAccount(accountNumber));
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody AccountMovimentRequest request){
        service.deposit(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody AccountMovimentRequest request){
        service.withdraw(request);
        return ResponseEntity.ok().build();
    }
}
