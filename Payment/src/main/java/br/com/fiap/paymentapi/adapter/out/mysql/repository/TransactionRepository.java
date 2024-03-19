package br.com.fiap.paymentapi.adapter.out.mysql.repository;

import br.com.fiap.paymentapi.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
