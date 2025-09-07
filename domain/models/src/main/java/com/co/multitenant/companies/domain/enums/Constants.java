package com.co.multitenant.companies.domain.enums;

public enum Constants {
    DEFAULT("DEFAULT"),
    X_TENANT("X-Tenant");

    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
