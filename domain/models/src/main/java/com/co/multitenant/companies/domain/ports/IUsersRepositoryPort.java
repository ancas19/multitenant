package com.co.multitenant.companies.domain.ports;

import com.co.multitenant.companies.domain.models.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUsersRepositoryPort {
    Users save(Users user);
    Optional<Users> findByEmail(String email);
    Optional<Users> findById(UUID id);
    List<Users> findAll();
    boolean existsByEmail(String email);
}
