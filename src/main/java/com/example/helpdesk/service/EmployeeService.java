package com.example.helpdesk.service;

import com.example.helpdesk.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee getById(Integer employeeId);

    void save (Employee employee);

    void delete (Integer clientId);
}
