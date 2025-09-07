package com.co.multitenant.companies.clients.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="company")
public class CompanyEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "domain")
    private String domain;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @Column(name = "active")
    private String active;
}
