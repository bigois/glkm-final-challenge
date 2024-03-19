package br.com.fiap.paymentapi.domain.model;

public enum Bank {
    ITAU("Itaú Unibanco S.A"), NU("NU Bank");

    private final String description;

    Bank(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
