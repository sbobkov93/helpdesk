package com.example.helpdesk.validation;

import com.example.helpdesk.entity.Client;
import com.example.helpdesk.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientValidatorImpl implements ClientValidator {

    private ClientService clientService;

    @Autowired
    public ClientValidatorImpl(ClientService clientService) {
        this.clientService = clientService;
    }


    @Override
    public List<ObjectError> validate(Client client) {
        List<ObjectError> errors = new ArrayList<>();
        Optional<Client> byPrefix = clientService.findByPrefix(client.getPrefix());
        if (byPrefix.isPresent()) {
            FieldError fieldError = new FieldError("client", "prefix", client.getPrefix(),
                    false, null, null, "Такой префикс уже существует");
            errors.add(fieldError);
        }
        Optional<Client> byShortName = clientService.findByShortName(client.getShortName());
        if (byShortName.isPresent()){
            FieldError fieldError = new FieldError("client", "shortName", client.getShortName(),
                    false, null, null,  "Такое краткое наименование уже существует");
            errors.add(fieldError);
        }
        Optional<Client> byName = clientService.findByName(client.getName());
        if (byName.isPresent()){
            FieldError fieldError = new FieldError("client", "name", client.getName(),
                    false, null, null, "Такое полное наименование уже существует");
            errors.add(fieldError);
        }
        return errors;
    }


}
