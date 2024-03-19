package br.com.fiap.paymentapi.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    private BigDecimal totalAmmount;
    private String originAccount;
    private String originAgency;
    private String targetAccount;
    private String targetAgency;
    private String targetAccountName;
    private String status = "False";

    public Transaction(BigDecimal totalAmmount, String originAccount,
                       String originAgency,
                       String targetAccount,
                       String targetAgency) {
        setTotalAmmount(totalAmmount);
        setOriginAccount(originAccount);
        setOriginAgency(originAgency);
        setTargetAccount(targetAccount);
        setTargetAgency(targetAgency);
    }

}
