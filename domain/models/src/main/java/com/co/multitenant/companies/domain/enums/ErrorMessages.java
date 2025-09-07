package com.co.multitenant.companies.domain.enums;

public enum ErrorMessages {

    ERROR_MESSAGE_DATABASE_CONFIGURATION_NOT_FOUND("There are no database configurations registered"),
    ERROR_MESSAGE_DATABASE_CONFIGURATION_NOT_FOUND_BY_DOMAIN("There is no database configuration for the specified domain"),
    ERROR_MESSAGE_ROLE_NOT_FOUND("Role not found"),
    ERROR_MESSAGE_EMAIL_ALREADY_EXISTS("Email already exists"),
    ERROR_MESSAGE_USER_NOT_FOUND("User not found"),
    ERROR_MESSAGE_USERS_NOT_FOUND("No users found");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
