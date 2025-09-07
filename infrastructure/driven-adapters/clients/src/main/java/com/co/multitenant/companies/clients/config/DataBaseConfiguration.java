package com.co.multitenant.companies.clients.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

public abstract class DataBaseConfiguration {

    protected DataBaseConfiguration() {
    }

    protected LocalContainerEntityManagerFactoryBean getEntityManager(String showSql, String hbm2ddl,
                                                                      String dialect, String schema, String[] packagesToScan,
                                                                      DataSource datasource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(datasource);
        em.setPackagesToScan(packagesToScan);
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap();
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
        properties.put("hibernate.default_schema",schema);
        properties.put("hibernate.dialect", dialect);
        em.setJpaPropertyMap(properties);
        return em;
    }

    protected DataSource getDataSource(String className, String jdbcUrl, String jdbcUser, String jdbcPass) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(className);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUser);
        dataSource.setPassword(jdbcPass);
        return dataSource;
    }

    protected PlatformTransactionManager getTransactionManager(LocalContainerEntityManagerFactoryBean factory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factory.getObject());
        return transactionManager;
    }

}
