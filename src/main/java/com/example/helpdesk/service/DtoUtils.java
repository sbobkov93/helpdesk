package com.example.helpdesk.service;

import com.example.helpdesk.dto.NoteDTO;
import com.example.helpdesk.dto.TicketDTO;
import com.example.helpdesk.dto.EmployeeDTO;
import com.example.helpdesk.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class DtoUtils {

    private final ModelMapper modelMapper;

    @Autowired
    public DtoUtils(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TicketDTO getTicketDTO(Authentication authentication, Ticket ticket) {
        boolean readOnly = isReadOnly(authentication, ticket);
        boolean isLastUpdate = ticket.getNotes().isEmpty();
        TicketDTO ticketDTO = modelMapper.map(ticket, TicketDTO.class);
        ticketDTO.setReadOnly(readOnly);
        ticketDTO.setLastUpdate(isLastUpdate);
        return ticketDTO;
    }

    private boolean isReadOnly(Authentication authentication, HelpdeskRecord record) {
        String creatorUserName = record.getCreator().getAuthenticationData().getUserName();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(UserRole.ROLE_ADMIN.name()));
        boolean isCreator = authentication.getName().equals(creatorUserName);
        return !isCreator && !isAdmin;
    }

    public Ticket getTicket(TicketDTO ticketDTO, Employee creator) {
        if (ticketDTO.getCreator() == null)
            ticketDTO.setCreator(creator.getId());
        return modelMapper.map(ticketDTO, Ticket.class);
    }

    public Ticket getTicket(TicketDTO ticketDTO) {
        return modelMapper.map(ticketDTO, Ticket.class);
    }

    public NoteDTO getNoteDTO(Authentication authentication, Note note) {
        boolean readOnly = isReadOnly(authentication, note);
        NoteDTO noteDTO = modelMapper.map(note, NoteDTO.class);
        noteDTO.setReadOnly(readOnly);
        return noteDTO;
    }

    public Employee getEmployee(EmployeeDTO user) {
        return modelMapper.map(user, Employee.class);
    }

    public EmployeeDTO getEmployeeDto(Employee employee, Authentication authentication){
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        employeeDTO.setReadOnly(isReadOnly(employee, authentication));
        return  employeeDTO;
    }

    private boolean isReadOnly(Employee employee, Authentication authentication){
        String userName = employee.getAuthenticationData().getUserName();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(UserRole.ROLE_ADMIN.name()));
        boolean isHimself = userName.equals(authentication.getName());
        return !isAdmin && !isHimself;
    }
}
