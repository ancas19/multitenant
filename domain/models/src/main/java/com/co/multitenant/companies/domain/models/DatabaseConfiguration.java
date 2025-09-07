package com.co.multitenant.companies.domain.models;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DatabaseConfiguration {
    private String databaseUrl;
    private String databaseUsername;
    private String databasePassword;
    private String defaultSchema;
    private String domain;
}
