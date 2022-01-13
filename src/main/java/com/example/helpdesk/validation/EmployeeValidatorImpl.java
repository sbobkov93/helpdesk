package com.example.helpdesk.validation;

import com.example.helpdesk.dto.EmployeeDTO;
import com.example.helpdesk.entity.AuthenticationData;
import com.example.helpdesk.entity.EmployeeDetails;
import com.example.helpdesk.repository.AuthenticationDataRepository;
import com.example.helpdesk.service.AuthenticationDataService;
import com.example.helpdesk.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeValidatorImpl implements EmployeeValidator {

    private final AuthenticationDataService authenticationDataService;
    private final EmployeeDetailsService employeeDetailsService;

    @Autowired
    public EmployeeValidatorImpl(AuthenticationDataService authenticationDataService, EmployeeDetailsService employeeDetailsService) {
        this.authenticationDataService = authenticationDataService;
        this.employeeDetailsService = employeeDetailsService;
    }

    @Override
    public List<ObjectError> validate(EmployeeDTO employeeDTO) {
        List<ObjectError> errors = new ArrayList<>();
        AuthenticationData data = authenticationDataService.findAuthenticationDataByUserName(employeeDTO.getUserName());
        if (data != null){
            FieldError fieldError = new FieldError("employee", "userName", employeeDTO.getUserName(),
                    false, null, null, "Такой логин уже существует");
            errors.add(fieldError);
        }
        Optional<EmployeeDetails> byPhone = employeeDetailsService.findByPhone(employeeDTO.getPhone());
        if (byPhone.isPresent()){
            FieldError fieldError = new FieldError("employee", "phone", employeeDTO.getPhone(),
                    false, null, null, "Такой телефон уже используется");
            errors.add(fieldError);
        }
        Optional<EmployeeDetails> byEmail = employeeDetailsService.findByEmail(employeeDTO.getEmail());
        if (byEmail.isPresent()){
            FieldError fieldError = new FieldError("employee", "email", employeeDTO.getEmail(),
                    false, null, null, "Такой email уже используется");
            errors.add(fieldError);
        }
        return errors;
    }
}
