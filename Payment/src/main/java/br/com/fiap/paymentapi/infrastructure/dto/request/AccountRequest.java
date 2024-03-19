package br.com.fiap.paymentapi.infrastructure.dto.request;


import br.com.fiap.paymentapi.domain.model.AccountOwner;
import br.com.fiap.paymentapi.domain.model.AccountType;
import br.com.fiap.paymentapi.domain.model.Bank;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequest {

    @JsonProperty("agency")
    private String agency;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("account_type")
    private AccountType accountType;
    @JsonProperty("bank_name")
    private Bank bankName;
    @JsonProperty("account_owner")
    private AccountOwner accountOwner;
    @JsonProperty("balance")
    private BigDecimal balance;

}
