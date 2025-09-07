package com.co.multitenant.companies.postgres.adapters;

import com.co.multitenant.companies.domain.models.Role;
import com.co.multitenant.companies.domain.ports.IRolesRepositroyPort;
import com.co.multitenant.companies.domain.utils.Mapper;
import com.co.multitenant.companies.postgres.repositories.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolesRepositoryAdapter  implements IRolesRepositroyPort {
    private final RolesRepository rolesRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return this.rolesRepository.findByName(name)
                .map(rolesEntity -> Mapper.map(rolesEntity,Role.class));
    }
}
