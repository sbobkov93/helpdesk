package com.example.helpdesk.service;

import com.example.helpdesk.entity.AuthenticationData;
import com.example.helpdesk.repository.AuthenticationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private AuthenticationDataRepository repository;

    @Autowired
    public UserDetailServiceImpl(AuthenticationDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationData data = repository.findAuthenticationDataByUserName(username);
        return new User(data.getUserName(), data.getPassword(), new ArrayList<>());
    }
}
