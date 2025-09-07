package com.co.multitenant.companies.postgres.adapters;

import com.co.multitenant.companies.domain.models.Users;
import com.co.multitenant.companies.domain.ports.IUsersRepositoryPort;
import com.co.multitenant.companies.domain.utils.Mapper;
import com.co.multitenant.companies.postgres.entities.UsersEntity;
import com.co.multitenant.companies.postgres.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersRepositoryAdapter implements IUsersRepositoryPort {
    private final UsersRepository usersRepository;

    @Override
    public Users save(Users user) {
        return Mapper.map(this.usersRepository.save(Mapper.map(user, UsersEntity.class)), Users.class);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return this.usersRepository.findByEmail(email)
                .map(usersEntity -> Mapper.map(usersEntity, Users.class));
    }

    @Override
    public Optional<Users> findById(UUID id) {
        return this.usersRepository.findById(id)
                .map(usersEntity -> Mapper.map(usersEntity, Users.class));
    }

    @Override
    public List<Users> findAll() {
        return this.usersRepository.findAll()
                .stream()
                .map(usersEntity -> Mapper.map(usersEntity, Users.class))
                .toList();
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.usersRepository.existsByEmail(email);
    }
}
