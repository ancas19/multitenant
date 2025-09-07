package com.co.multitenant.companies.clients.config;


import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.co.multitenant.companies.clients.repositories",
        entityManagerFactoryRef = "getPostgresEntityManager",
        transactionManagerRef = "getPostgresTransactionManager"
)
@EntityScan(basePackages ="com.co.multitenant.companies.clients.entities")
@NoArgsConstructor

public class PostgresConfiguration extends DataBaseConfiguration {

    @Value("${postgres.hibernate.show_sql}")
    private String showSql;
    @Value("${postgres.hibernate.hbm2ddl.auto}")
    private String hbm2ddl;
    @Value("${postgres.dialect}")
    private String dialect;
    @Value("${postgres.driverClassName}")
    private String driverClassName;
    @Value("${postgres.url}")
    private String urlDatabse;
    @Value("${postgres.user}")
    private String userDatabase;
    @Value("${postgres.pass}")
    private String passwordDatabase;
    @Value("${postgres.default_schema}")
    private String schema;

    @Bean(name="getPostgresEntityManager")
    public LocalContainerEntityManagerFactoryBean getPostgresEntityManager() {
        return super.getEntityManager(showSql, hbm2ddl, dialect,schema, new String[]{"com.co.multitenant.companies.clients"}, getPostgresDataSource());
    }

    @Bean
    public DataSource getPostgresDataSource() {
        return super.getDataSource(driverClassName, urlDatabse, userDatabase, passwordDatabase);
    }

    @Bean(name="getPostgresTransactionManager")
    public PlatformTransactionManager getPostgresTransactionManager() {
        return super.getTransactionManager(getPostgresEntityManager());
    }

}

