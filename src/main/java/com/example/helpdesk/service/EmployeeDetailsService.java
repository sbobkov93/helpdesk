package com.example.helpdesk.service;

import com.example.helpdesk.entity.EmployeeDetails;

import java.util.Optional;

public interface EmployeeDetailsService {

    Optional<EmployeeDetails> findByPhone(String phone);
    Optional<EmployeeDetails> findByEmail(String email);

}
