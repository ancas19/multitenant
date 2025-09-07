package com.co.multitenant.companies.clients.repositories;

import com.co.multitenant.companies.clients.entities.DatabaseConfigurationEntity;
import com.co.multitenant.companies.domain.models.DatabaseConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DatabaseConfigurationRepository extends JpaRepository<DatabaseConfigurationEntity, UUID> {

    @Query(
           """
           SELECT new com.co.multitenant.companies.domain.models.DatabaseConfiguration(
                d.databaseUrl,
                d.databaseUsername,
                d.databasePassword,
                d.defaultSchema,
                c.domain
           )
           FROM DatabaseConfigurationEntity d
           INNER JOIN CompanyEntity c ON d.companyId = c.id
           WHERE c.domain != 'DEFAULT'
           """
    )
    List<DatabaseConfiguration> findAllWithoutDefault();

    @Query(
            """
             SELECT new com.co.multitenant.companies.domain.models.DatabaseConfiguration(
                d.databaseUrl,
                d.databaseUsername,
                d.databasePassword,
                d.defaultSchema,
                c.domain
           )
           FROM DatabaseConfigurationEntity d
           INNER JOIN CompanyEntity c ON d.companyId = c.id
           WHERE c.domain = :domain
            """
    )
    Optional<DatabaseConfiguration> findByDomain(@Param("domain") String domain);
}
