package com.co.multitenant.companies.adapters;

import com.co.multitenant.companies.domain.exceptions.NotFoundException;
import com.co.multitenant.companies.domain.models.Role;
import com.co.multitenant.companies.domain.ports.IRolesRepositroyPort;
import com.co.multitenant.companies.ports.IRolePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.co.multitenant.companies.domain.enums.ErrorMessages.ERROR_MESSAGE_ROLE_NOT_FOUND;

@RequiredArgsConstructor
public class RolesAdapter implements IRolePort {
    private final IRolesRepositroyPort rolesRepositroyPort;
    @Override
    public Role findByName(String name) {
        Optional<Role> roleFound = rolesRepositroyPort.findByName(name);
        if(roleFound.isEmpty()){
            throw new NotFoundException(ERROR_MESSAGE_ROLE_NOT_FOUND.getMessage());
        }
        return roleFound.get();
    }
}
