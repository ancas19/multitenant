package com.co.multitenant.companies.domain.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Users {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UUID roleId;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
