package br.com.fiap.techchallenge5authenticatelogin.request;


import br.com.fiap.techchallenge5authenticatelogin.entity.enums.UserRole;

public record UserRequest(String login, String password, UserRole role) {
}
