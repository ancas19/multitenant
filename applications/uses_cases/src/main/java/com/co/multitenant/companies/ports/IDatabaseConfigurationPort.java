package com.co.multitenant.companies.ports;

import com.co.multitenant.companies.domain.models.DatabaseConfiguration;

import java.util.List;

public interface IDatabaseConfigurationPort {
    List<DatabaseConfiguration> findAll();
    DatabaseConfiguration findByDommain(String domain);
}
