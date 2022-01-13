package com.example.helpdesk.validation;

import com.example.helpdesk.dto.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketUpdateValidatorServiceImpl implements TicketUpdateValidatorService {

    private final List<TicketUpdateValidator> validators;

    @Autowired
    public TicketUpdateValidatorServiceImpl(List<TicketUpdateValidator> validators) {
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
