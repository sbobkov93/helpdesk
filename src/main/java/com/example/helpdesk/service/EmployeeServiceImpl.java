package com.example.helpdesk.service;

import com.example.helpdesk.entity.Employee;
import com.example.helpdesk.repository.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Optional<Employee> findById(Integer employeeId) {
        return employeeDao.findById(employeeId);
    }

    @Override
    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public void delete(Integer clientId) {
        employeeDao.deleteById(clientId);
    }

    @Override
    public Employee findByAuthenticationDataUserName(String userName) {
        return employeeDao.findByAuthenticationData_UserName(userName);
    }

    @Override
    public Employee findByIdEager(Integer employeeId) {
        return employeeDao.findByIdEager(employeeId);
    }
}
