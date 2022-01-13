package com.example.helpdesk.validation;

import com.example.helpdesk.entity.Client;
import org.springframework.validation.ObjectError;

import java.util.List;

public interface ClientValidator {

    List<ObjectError> validate(Client client);

}
