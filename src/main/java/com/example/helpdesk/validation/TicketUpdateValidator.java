package com.example.helpdesk.validation;

import com.example.helpdesk.dto.TicketDTO;
import org.springframework.security.core.Authentication;

public interface TicketUpdateValidator {

    ValidationResult validate (TicketDTO ticketDTO, Authentication authentication);

}
