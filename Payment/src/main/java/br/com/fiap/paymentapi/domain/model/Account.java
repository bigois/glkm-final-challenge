package br.com.fiap.paymentapi.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String agency;
    private String accountNumber;
    private String accountType;
    private String Banco;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private AccountOwner accountOwner;
    private String accountOwnerName;
    private BigDecimal balance;
    private BigDecimal blockedBalance = new BigDecimal("0.0");

    public Account(String agency, String accountNumber,
                   String accountType, String banco,
                   AccountOwner accountOwner,
                   String accountOwnerName,
                   BigDecimal balance) {
        setAgency(agency);
        setAccountNumber(accountNumber);
        setAccountType(accountType);
        setAccountOwner(accountOwner);
        setBanco(banco);
        setAccountOwnerName(accountOwnerName);
        setBalance(balance);

    }
}
