package com.co.multitenant.companies.clients.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "database_configuration")
public class DatabaseConfigurationEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "database_url")
    private String databaseUrl;

    @Column(name = "database_username")
    private String databaseUsername;

    @Column(name = "database_password")
    private String databasePassword;

    @Column(name = "default_schema")
    private String defaultSchema;

    @Column(name = "company_id")
    private UUID companyId;
}
