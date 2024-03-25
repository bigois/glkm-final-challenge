package com.fiap.tech.Gateway.dtos;

import jakarta.validation.constraints.NotBlank;

public record AccountOwnerDTO (
        @NotBlank(message = "name must not be blank")
        String name,
        @NotBlank(message = "email must not be blank")
        String email
){}
