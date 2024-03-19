package br.com.fiap.paymentapi.infrastructure.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AccountMovimentRequest {

    @JsonProperty("account_number")
    String accountNumber;
    @JsonProperty("account_agency")
    String accountAgency;
    @JsonProperty("ammount")
    BigDecimal ammount;
}
