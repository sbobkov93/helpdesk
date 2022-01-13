package com.example.helpdesk.repository;

import com.example.helpdesk.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Integer> {

    Optional<EmployeeDetails> findByPhone(String phone);
    Optional<EmployeeDetails> findByEmail(String email);

}
