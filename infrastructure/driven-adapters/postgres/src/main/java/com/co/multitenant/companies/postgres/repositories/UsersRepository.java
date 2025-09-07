package com.co.multitenant.companies.postgres.repositories;

import com.co.multitenant.companies.domain.utils.Mapper;
import com.co.multitenant.companies.postgres.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, UUID> {
    Optional<UsersEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
