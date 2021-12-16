package com.example.helpdesk.service;

import com.example.helpdesk.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAll();

    Optional<Employee> findById(Integer employeeId);

    void save (Employee employee);

    void delete (Integer clientId);

    Employee findByAuthenticationDataUserName(String userName);
}
