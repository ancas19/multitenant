package com.co.multitenant.companies.postgres.repositories;

import com.co.multitenant.companies.postgres.entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, UUID> {
    Optional<RolesEntity> findByName(String name);
}
