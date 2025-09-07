package com.co.multitenant.companies.postgres.config;

import com.co.multitenant.companies.domain.enums.Constants;
import com.co.multitenant.companies.domain.models.DatabaseConfiguration;
import com.co.multitenant.companies.domain.ports.IDatabaseConfigurationAppServicePort;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages ="com.co.multitenant.companies.postgres.repositories",
        entityManagerFactoryRef = "getPostgresEntityManagerClientes",
        transactionManagerRef = "TransactionManagerClientes"
)
public class ClientesPostgresConfiguracion {
    @Value("${postgres.driverClassName}")
    private String driverClassName;
    @Value("${postgres.dialect}")
    private String dialect;
    @Value("${postgres.hibernate.show_sql}")
    private String showSql;
    @Value("${postgres.hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;
    @Value("${poolSize}")
    private Integer poolSize;
    @Value("${time_life_conection}")
    private Integer timeLife;
    @Value("${connectionTimeout}")
    private Integer connectionTimeout;
    private final IDatabaseConfigurationAppServicePort configuracionClientesAppServicePort;

    public ClientesPostgresConfiguracion(IDatabaseConfigurationAppServicePort configuracionClientesAppServicePort) {
        this.configuracionClientesAppServicePort = configuracionClientesAppServicePort;
    }


    @Bean
    @ConfigurationProperties(prefix = "tenants")
    public DataSource dataSource()  {
        AbstractRoutingDataSource dataSource = new MultitenantDataSource();
        dataSource.setDefaultTargetDataSource(buildDataSource(Constants.DEFAULT.getValue()));
        dataSource.setTargetDataSources(buildAllDataSources());
        dataSource.afterPropertiesSet();
        return dataSource;
    }

    private DataSource buildDataSource(String tenantId)  {
        DatabaseConfiguration configuracionCliente = this.configuracionClientesAppServicePort.findByDommain(tenantId);
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setUsername(configuracionCliente.getDatabaseUsername());
        config.setPassword(configuracionCliente.getDatabasePassword());
        config.setJdbcUrl(configuracionCliente.getDatabaseUrl());
        config.setMaximumPoolSize(poolSize);
        config.setMaxLifetime(timeLife);
        config.setConnectionTimeout(connectionTimeout);
        config.setSchema(configuracionCliente.getDefaultSchema());
        return new HikariDataSource(config);
    }


    private Map<Object, Object> buildAllDataSources()  {
        Map<Object, Object> resolvedDataSources = new HashMap<>();
        List<DatabaseConfiguration> allConfigs = configuracionClientesAppServicePort.findAll();
        for (DatabaseConfiguration configuracionCliente : allConfigs) {
            HikariConfig config = new HikariConfig();
            config.setDriverClassName(driverClassName);
            config.setUsername(configuracionCliente.getDatabaseUsername());
            config.setPassword(configuracionCliente.getDatabasePassword());
            config.setJdbcUrl(configuracionCliente.getDatabaseUrl());
            config.setMaximumPoolSize(poolSize);
            config.setMaxLifetime(timeLife);
            config.setConnectionTimeout(connectionTimeout);
            resolvedDataSources.put(configuracionCliente.getDomain(),
                    new HikariDataSource(config)
            );
        }

        return resolvedDataSources;
    }

    @Bean(name = "getPostgresEntityManagerClientes")
    public LocalContainerEntityManagerFactoryBean tenantEntityManagerFactory()  {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.co.multitenant.companies.postgres");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties  properties = new Properties();
        properties.setProperty("hibernate.dialect",dialect);
        properties.setProperty("hibernate.show_sql",showSql);
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        em.setJpaProperties(properties);
        return em;
    }

    @Bean(name = "TransactionManagerClientes")
    public PlatformTransactionManager tenantTransactionManager(@Qualifier("getPostgresEntityManagerClientes") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

