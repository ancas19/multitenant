package com.co.multitenant.companies.services;

import com.co.multitenant.companies.domain.models.Users;
import com.co.multitenant.companies.domain.utils.Mapper;
import com.co.multitenant.companies.ports.IUsersPort;
import com.co.multitenant.companies.request.UserRequest;
import com.co.multitenant.companies.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersAppService {
    private final IUsersPort usersPort;

    @Transactional(value = "TransactionManagerClientes",rollbackFor = Exception.class)
    public UserResponse saveUser(UserRequest userRequest) {
        return Mapper.map(this.usersPort.save(Mapper.map(userRequest, Users.class),userRequest.getRoleName()), UserResponse.class);
    }

    @Transactional(value = "TransactionManagerClientes",rollbackFor = Exception.class)
    public UserResponse findByEmail(String email){
        return Mapper.map(usersPort.findByEmail(email),UserResponse.class);
    }

    @Transactional(value = "TransactionManagerClientes",rollbackFor = Exception.class)
    public UserResponse findById(UUID id){
        return Mapper.map(usersPort.findById(id),UserResponse.class);
    }

    @Transactional(value = "TransactionManagerClientes",rollbackFor = Exception.class)
    public List<UserResponse> findAll(){
        return Mapper.mapAll(this.usersPort.findAll(), UserResponse.class);
    }

}
