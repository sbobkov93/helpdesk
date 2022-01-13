package com.example.helpdesk.validation;

import com.example.helpdesk.dto.TicketDTO;
import org.springframework.security.core.Authentication;

public interface TicketUpdateValidatorService {

    ValidationResult validate (TicketDTO ticketDTO, Authentication authentication);

}
