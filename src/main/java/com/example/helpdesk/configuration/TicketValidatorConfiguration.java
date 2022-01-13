package com.example.helpdesk.configuration;

import com.example.helpdesk.dto.TicketDTO;
import com.example.helpdesk.entity.Employee;
import com.example.helpdesk.entity.Ticket;
import com.example.helpdesk.service.EmployeeService;
import com.example.helpdesk.service.TicketService;
import com.example.helpdesk.validation.TicketUpdateValidator;
import com.example.helpdesk.validation.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Optional;

import static com.example.helpdesk.entity.UserRole.ROLE_ADMIN;

@Configuration
public class TicketValidatorConfiguration {

    private final TicketService ticketService;
    private final EmployeeService employeeService;


    @Autowired
    public TicketValidatorConfiguration(TicketService ticketService, EmployeeService employeeService) {
        this.ticketService = ticketService;
        this.employeeService = employeeService;
    }

    @Bean
    @Order(1)
    public TicketUpdateValidator isPresent(){
        return (t, a) -> ValidationResult.builder()
                .isValid(ticketService.isExistInDb(t.getId()))
                .errorMessage("No ticket with such id: " + t.getId())
                .build();
    }

    @Bean
    @Order(2)
    public TicketUpdateValidator isAuthorized(){
        return (t, a) -> {
            boolean isAdmin = a.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(ROLE_ADMIN.name()));
            Ticket ticket = getActualTicket(t);
            Employee requester = employeeService.findByAuthenticationDataUserName(a.getName());
            boolean isCreator = ticket.getCreator().equals(requester);
            return ValidationResult.builder()
                    .isValid(isAdmin || isCreator)
                    .errorMessage("Not authorized")
                    .build();
        };
    }

    @Bean
    @Order(3)
    public TicketUpdateValidator isClientUnchanged(){
        return (t, a) -> {
            Ticket ticket = getActualTicket(t);
            boolean isUnchanged = t.getClient().equals(ticket.getClient().getId());
            return ValidationResult.builder()
                    .isValid(isUnchanged)
                    .errorMessage("Attempt to change immutable field client")
                    .build();
        };
    }

    @Bean
    @Order(4)
    public TicketUpdateValidator isCreatorUnchanged(){
        return (t, a) -> {
            Ticket actualTicket = getActualTicket(t);
            boolean isUnchanged = t.getCreator().equals(actualTicket.getCreator().getId());
            return ValidationResult.builder()
                    .isValid(isUnchanged)
                    .errorMessage("Attempt to change immutable field creator")
                    .build();
        };
    }

    @Bean
    @Order(5)
    public TicketUpdateValidator isStatusValid(){
        return (t, a) -> {
            Ticket actualTicket = getActualTicket(t);
            boolean isUnchanged = t.getStatus().equals(actualTicket.getStatus().getId());
            boolean isLastUpdate = actualTicket.getNotes().isEmpty();
            return ValidationResult.builder()
                    .isValid(isUnchanged || isLastUpdate)
                    .errorMessage("Attempt to change status directly when notes are present")
                    .build();
        };
    }

    @Bean
    @Order(6)
    public TicketUpdateValidator isOwnerValid(){
        return (t, a) -> {
            Ticket actualTicket = getActualTicket(t);
            boolean isUnchanged = t.getOwner().equals(actualTicket.getOwner().getId());
            boolean isLastUpdate = actualTicket.getNotes().isEmpty();
            return ValidationResult.builder()
                    .isValid(isUnchanged || isLastUpdate)
                    .errorMessage("Attempt to change owner directly when notes are present")
                    .build();
        };
    }

    private Ticket getActualTicket(TicketDTO t) {
        Optional<Ticket> optionalTicket = ticketService.findByIdWithNotes(t.getId());
        return optionalTicket
                .orElseThrow(() -> new IllegalArgumentException("No ticket with such id: " + t.getId()));
    }


}
