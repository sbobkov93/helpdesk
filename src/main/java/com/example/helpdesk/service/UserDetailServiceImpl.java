package com.example.helpdesk.service;

import com.example.helpdesk.entity.AuthenticationData;
import com.example.helpdesk.repository.AuthenticationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(data.getRole().getRole().name()));
        return new User(data.getUserName(), data.getPassword(), authorities);
    }
}
