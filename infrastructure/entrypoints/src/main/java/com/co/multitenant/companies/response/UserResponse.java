package com.co.multitenant.companies.response;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UUID roleId;
    private Boolean status;
}
