package br.com.fiap.paymentapi.adapter.out.mysql.repository;

import br.com.fiap.paymentapi.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByAccountNumber(String accountNumber);
    Optional<Account> findAccountByAccountNumberAndAgency(String accountNumber, String agency);
}
