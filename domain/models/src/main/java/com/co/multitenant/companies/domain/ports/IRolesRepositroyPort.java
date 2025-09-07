package com.co.multitenant.companies.domain.ports;

import com.co.multitenant.companies.domain.models.Role;

import java.util.Optional;

public interface IRolesRepositroyPort {
    Optional<Role> findByName(String name);
}
