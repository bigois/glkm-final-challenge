package br.com.fiap.paymentapi.infrastructure.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountOwnerRequest {

    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;

}
