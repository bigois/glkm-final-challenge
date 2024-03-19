package br.com.fiap.paymentapi.domain.model;

public enum AccountType {
    CHECKING_ACCOUNT("Checking account"), SAVING_ACCOUNT("Saving account");

    private final String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}

