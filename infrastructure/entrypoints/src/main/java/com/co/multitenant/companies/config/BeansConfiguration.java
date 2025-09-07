package com.co.multitenant.companies.config;

import com.co.multitenant.companies.adapters.DatabaseConfigurationAdapter;
import com.co.multitenant.companies.adapters.RolesAdapter;
import com.co.multitenant.companies.adapters.UsersAdapter;
import com.co.multitenant.companies.domain.ports.IDatabaseConfigurationRepositroyPort;
import com.co.multitenant.companies.domain.ports.IRolesRepositroyPort;
import com.co.multitenant.companies.domain.ports.IUsersRepositoryPort;
import com.co.multitenant.companies.ports.IDatabaseConfigurationPort;
import com.co.multitenant.companies.ports.IRolePort;
import com.co.multitenant.companies.ports.IUsersPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public IDatabaseConfigurationPort databaseConfigurationPort( IDatabaseConfigurationRepositroyPort databaseConfigurationRepositroyPort) {
        return new DatabaseConfigurationAdapter(databaseConfigurationRepositroyPort);
    }

    @Bean
    public IRolePort rolePort(IRolesRepositroyPort rolesRepositroyPort) {
        return new RolesAdapter(rolesRepositroyPort);
    }

    @Bean
    public IUsersPort usersPort(IUsersRepositoryPort usersRepositoryPort, IRolePort rolePort) {
        return new UsersAdapter(usersRepositoryPort,rolePort);
    }
}
