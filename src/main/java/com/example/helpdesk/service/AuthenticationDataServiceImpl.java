package com.example.helpdesk.service;

import com.example.helpdesk.entity.AuthenticationData;
import com.example.helpdesk.repository.AuthenticationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationDataServiceImpl implements AuthenticationDataService {

    private AuthenticationDataRepository repository;

    @Autowired
    public AuthenticationDataServiceImpl(AuthenticationDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthenticationData findAuthenticationDataByUserName(String userName) {
        return repository.findAuthenticationDataByUserName(userName);
    }
}
