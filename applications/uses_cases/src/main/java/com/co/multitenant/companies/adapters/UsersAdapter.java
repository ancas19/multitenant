package com.co.multitenant.companies.adapters;

import com.co.multitenant.companies.domain.exceptions.BadRequestException;
import com.co.multitenant.companies.domain.exceptions.NotFoundException;
import com.co.multitenant.companies.domain.models.Role;
import com.co.multitenant.companies.domain.models.Users;
import com.co.multitenant.companies.domain.ports.IUsersRepositoryPort;
import com.co.multitenant.companies.ports.IRolePort;
import com.co.multitenant.companies.ports.IUsersPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.co.multitenant.companies.domain.enums.ErrorMessages.*;

@RequiredArgsConstructor
public class UsersAdapter implements IUsersPort {
    private final IUsersRepositoryPort usersRepositoryPort;
    private final IRolePort rolePort;
    @Override
    public Users save(Users user,String rolename) {
        boolean existsByEmail= this.usersRepositoryPort.existsByEmail(user.getEmail());
        if(existsByEmail){
            throw new BadRequestException(ERROR_MESSAGE_EMAIL_ALREADY_EXISTS.getMessage());
        }
        Role roleFound=rolePort.findByName(rolename);
        user.setRoleId(roleFound.getId());
        return this.usersRepositoryPort.save(user);
    }

    @Override
    public Users findByEmail(String email) {
        Optional<Users> userFound=this.usersRepositoryPort.findByEmail(email);
        if (userFound.isEmpty()){
            throw new NotFoundException(ERROR_MESSAGE_USER_NOT_FOUND.getMessage());
        }
        return userFound.get();
    }

    @Override
    public Users findById(UUID id) {
        Optional<Users> userFound=this.usersRepositoryPort.findById(id);
        if(userFound.isEmpty()){
            throw new NotFoundException(ERROR_MESSAGE_USER_NOT_FOUND.getMessage());
        }
        return userFound.get();
    }

    @Override
    public List<Users> findAll() {
        List<Users> usersFound=this.usersRepositoryPort.findAll();
        if(usersFound.isEmpty()){
            throw new NotFoundException(ERROR_MESSAGE_USERS_NOT_FOUND.getMessage());
        }
        return usersFound;
    }
}
