package br.com.fiap.paymentapi.infrastructure.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {

    @JsonProperty("transaction_id")
    private Long id;
    @JsonProperty("date")
    private String date;
    @JsonProperty("total_ammount")
    private BigDecimal totalAmmount;
    @JsonProperty("origin_account")
    private String originAccount;
    @JsonProperty("origin_agency")
    private String originAgency;
    @JsonProperty("target_account")
    private String targetAccount;
    @JsonProperty("target_agency")
    private String targetAgency;
    @JsonProperty("target_account_name")
    private String targetAccountName;
    @JsonProperty("status")
    private String status;
}
