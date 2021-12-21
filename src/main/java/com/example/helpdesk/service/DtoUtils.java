package com.example.helpdesk.service;

import com.example.helpdesk.dto.NoteDTO;
import com.example.helpdesk.dto.TicketDTO;
import com.example.helpdesk.entity.Employee;
import com.example.helpdesk.entity.Note;
import com.example.helpdesk.entity.Ticket;
import com.example.helpdesk.entity.UserRole;
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

    private boolean isReadOnly(Authentication authentication, Ticket ticket) {
        String creatorUserName = ticket.getCreator().getAuthenticationData().getUserName();
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

    public NoteDTO getNoteDTO(Authentication authentication, Note note) {
        boolean readOnly = isReadOnly(authentication, note);
        NoteDTO noteDTO = modelMapper.map(note, NoteDTO.class);
        noteDTO.setReadOnly(readOnly);
        return noteDTO;
    }

    private boolean isReadOnly(Authentication authentication, Note note) {
        String creatorUserName = note.getCreator().getAuthenticationData().getUserName();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch( a -> a.getAuthority().equals(UserRole.ROLE_ADMIN.name()));
        boolean isCreator = authentication.getName().equals(creatorUserName);
        return !isAdmin && !isCreator;
    }

}
