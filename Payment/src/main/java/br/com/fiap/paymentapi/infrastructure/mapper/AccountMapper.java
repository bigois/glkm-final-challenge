package br.com.fiap.paymentapi.infrastructure.mapper;

import br.com.fiap.paymentapi.domain.model.Account;
import br.com.fiap.paymentapi.infrastructure.dto.request.AccountRequest;

public interface AccountMapper {

    Account requestToModel(AccountRequest request);
}
