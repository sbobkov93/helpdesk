package com.example.helpdesk.service;

import com.example.helpdesk.dto.TicketDTO;
import com.example.helpdesk.validation.TicketValidator;
import com.example.helpdesk.validation.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketUpdateValidatorImpl implements TicketUpdateValidator {

    private final List<TicketValidator> validators;

    @Autowired
    public TicketUpdateValidatorImpl(List<TicketValidator> validators) {
        this.validators = validators;
    }

    @Override
    public ValidationResult validate(TicketDTO ticketDTO, Authentication authentication) {
        return validators.stream()
                .map(v -> v.validate(ticketDTO, authentication))
                .filter(result -> !result.isValid())
                .findFirst()
                .orElse(ValidationResult.builder()
                        .isValid(true)
                        .errorMessage("none")
                        .build());
    }
}
