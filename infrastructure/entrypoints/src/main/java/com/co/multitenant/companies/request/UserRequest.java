package com.co.multitenant.companies.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRequest {
    @NotNull(message="the field is required")
    @NotEmpty(message="the field cannot be empty")
    private String firstName;
    @NotNull(message="the field is required")
    @NotEmpty(message="the field cannot be empty")
    private String lastName;
    @NotNull(message="the field is required")
    @NotEmpty(message="the field cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "the field must be a valid email")
    private String email;
    @NotNull(message="the field is required")
    @NotEmpty(message="the field cannot be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "the field must contain at least 8 characters, one uppercase letter, one lowercase letter, one number and one special character")
    private String password;
    @NotNull(message="the field is required")
    @NotEmpty(message="the field cannot be empty")
    private String roleName;
}
