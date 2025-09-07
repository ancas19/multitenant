package com.co.multitenant.companies.clients.adapters;

import com.co.multitenant.companies.clients.repositories.DatabaseConfigurationRepository;
import com.co.multitenant.companies.domain.models.DatabaseConfiguration;
import com.co.multitenant.companies.domain.ports.IDatabaseConfigurationRepositroyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DatabaseConfigurationRepositoryAdapter  implements IDatabaseConfigurationRepositroyPort {
    private final DatabaseConfigurationRepository databaseConfigurationRepository;
    @Override
    public List<DatabaseConfiguration> findAll() {
        return databaseConfigurationRepository.findAllWithoutDefault();
    }

    @Override
    public Optional<DatabaseConfiguration> findByDommain(String domain) {
        return this.databaseConfigurationRepository.findByDomain(domain);
    }
}
