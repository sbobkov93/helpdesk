package com.example.helpdesk.service;

import com.example.helpdesk.dto.TicketDTO;
import com.example.helpdesk.validation.ValidationResult;
import org.springframework.security.core.Authentication;

public interface TicketUpdateValidator {

    ValidationResult validate (TicketDTO ticketDTO, Authentication authentication);

}
