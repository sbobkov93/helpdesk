package com.example.helpdesk.validation;

import com.example.helpdesk.dto.TicketDTO;
import com.example.helpdesk.entity.Ticket;
import org.springframework.security.core.Authentication;

public interface TicketValidator {

    ValidationResult validate (TicketDTO ticketDTO, Authentication authentication);

}
