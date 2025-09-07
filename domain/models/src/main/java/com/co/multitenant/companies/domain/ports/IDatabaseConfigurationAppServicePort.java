package com.co.multitenant.companies.domain.ports;

import com.co.multitenant.companies.domain.models.DatabaseConfiguration;

import java.util.List;

public interface IDatabaseConfigurationAppServicePort {
    List<DatabaseConfiguration> findAll();
    DatabaseConfiguration findByDommain(String domain);
}
