package com.co.multitenant.companies.controllers;

import com.co.multitenant.companies.request.EmailRequest;
import com.co.multitenant.companies.request.UserRequest;
import com.co.multitenant.companies.response.UserResponse;
import com.co.multitenant.companies.services.UsersAppService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {
    private final UsersAppService usersAppService;

    @PostMapping()
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userRequest){
      return new ResponseEntity<>(this.usersAppService.saveUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/emails")
    public ResponseEntity<UserResponse> findByemail(@Valid @RequestBody EmailRequest emailRequest){
        return new ResponseEntity<>(this.usersAppService.findByEmail(emailRequest.getEmail()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable("id") UUID id){
        return new ResponseEntity<>(this.usersAppService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> findAll(){
        return new ResponseEntity<>(this.usersAppService.findAll(), HttpStatus.OK);
    }
}
