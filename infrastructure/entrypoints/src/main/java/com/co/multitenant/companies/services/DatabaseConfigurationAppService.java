package com.co.multitenant.companies.services;


import com.co.multitenant.companies.domain.models.DatabaseConfiguration;
import com.co.multitenant.companies.domain.ports.IDatabaseConfigurationAppServicePort;
import com.co.multitenant.companies.ports.IDatabaseConfigurationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseConfigurationAppService implements IDatabaseConfigurationAppServicePort {

    private final IDatabaseConfigurationPort databaseConfigurationPort;
    @Override
    @Transactional(value = "getPostgresTransactionManager",rollbackFor = Exception.class)
    public List<DatabaseConfiguration> findAll() {
        return this.databaseConfigurationPort.findAll();
    }

    @Override
    @Transactional(value = "getPostgresTransactionManager",rollbackFor = Exception.class)
    public DatabaseConfiguration findByDommain(String domain) {
        return this.databaseConfigurationPort.findByDommain(domain);
    }
}
