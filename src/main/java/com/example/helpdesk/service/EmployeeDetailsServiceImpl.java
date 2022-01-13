package com.example.helpdesk.service;

import com.example.helpdesk.entity.EmployeeDetails;
import com.example.helpdesk.repository.EmployeeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    private EmployeeDetailsRepository repository;

    @Autowired
    public EmployeeDetailsServiceImpl(EmployeeDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<EmployeeDetails> findByPhone(String phone) {
        return repository.findByPhone(phone);
    }

    @Override
    public Optional<EmployeeDetails> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
