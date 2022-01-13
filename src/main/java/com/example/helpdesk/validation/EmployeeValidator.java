package com.example.helpdesk.validation;

import com.example.helpdesk.dto.EmployeeDTO;
import org.springframework.validation.ObjectError;

import java.util.List;

public interface EmployeeValidator {

    List<ObjectError> validate (EmployeeDTO employeeDTO);

}
