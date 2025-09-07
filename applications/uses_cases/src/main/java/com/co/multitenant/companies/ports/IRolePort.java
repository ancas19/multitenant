package com.co.multitenant.companies.ports;

import com.co.multitenant.companies.domain.models.Role;

public interface IRolePort {
    Role findByName(String name);
}
