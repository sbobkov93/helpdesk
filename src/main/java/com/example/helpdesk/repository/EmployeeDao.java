package com.example.helpdesk.repository;

import com.example.helpdesk.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    Employee findByAuthenticationData_UserName(String userName);

    @Query("select e from Employee e join fetch e.authenticationData join fetch e.employeeDetails where e.id =?1")
    Employee findByIdEager(Integer id);

}
