package br.com.fiap.paymentapi.infrastructure.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PaymentRequest {

    @JsonProperty("origin_account_number")
    String originAccountNumber;
    @JsonProperty("origin_account_agency")
    String originAccountAgency;
    @JsonProperty("target_account_number")
    String targetAccountNumber;
    @JsonProperty("target_account_agency")
    String targetAccountAgency;
    @JsonProperty("ammount")
    BigDecimal ammount;
}
