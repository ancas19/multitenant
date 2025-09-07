package com.co.multitenant.companies.adapters;

import com.co.multitenant.companies.domain.exceptions.NotFoundException;
import com.co.multitenant.companies.domain.models.DatabaseConfiguration;
import com.co.multitenant.companies.domain.ports.IDatabaseConfigurationRepositroyPort;
import com.co.multitenant.companies.ports.IDatabaseConfigurationPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.co.multitenant.companies.domain.enums.ErrorMessages.ERROR_MESSAGE_DATABASE_CONFIGURATION_NOT_FOUND;
import static com.co.multitenant.companies.domain.enums.ErrorMessages.ERROR_MESSAGE_DATABASE_CONFIGURATION_NOT_FOUND_BY_DOMAIN;

@RequiredArgsConstructor
public class DatabaseConfigurationAdapter implements IDatabaseConfigurationPort {
    private final IDatabaseConfigurationRepositroyPort databaseConfigurationRepositroyPort;
    @Override
    public List<DatabaseConfiguration> findAll() {
        List<DatabaseConfiguration> databaseConfigurationsFound=this.databaseConfigurationRepositroyPort.findAll();
        if(databaseConfigurationsFound.isEmpty()){
            throw new NotFoundException(ERROR_MESSAGE_DATABASE_CONFIGURATION_NOT_FOUND.getMessage());
        }
        return databaseConfigurationsFound;
    }

    @Override
    public DatabaseConfiguration findByDommain(String domain) {
        Optional<DatabaseConfiguration> databaseConfigurationFound=this.databaseConfigurationRepositroyPort.findByDommain(domain);
        if(databaseConfigurationFound.isEmpty()){
            throw new NotFoundException(ERROR_MESSAGE_DATABASE_CONFIGURATION_NOT_FOUND_BY_DOMAIN.getMessage());
        }
        return databaseConfigurationFound.get();
    }
}
