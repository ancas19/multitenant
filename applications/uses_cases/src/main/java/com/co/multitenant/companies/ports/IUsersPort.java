package com.co.multitenant.companies.ports;

import com.co.multitenant.companies.domain.models.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUsersPort {
    Users save(Users user, String roleName);
    Users findByEmail(String email);
    Users findById(UUID id);
    List<Users> findAll();
}
