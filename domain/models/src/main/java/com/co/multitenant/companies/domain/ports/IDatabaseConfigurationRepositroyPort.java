package com.co.multitenant.companies.domain.ports;

import com.co.multitenant.companies.domain.models.DatabaseConfiguration;

import java.util.List;
import java.util.Optional;

public interface IDatabaseConfigurationRepositroyPort {
    List<DatabaseConfiguration> findAll();
    Optional<DatabaseConfiguration> findByDommain(String domain);
}
