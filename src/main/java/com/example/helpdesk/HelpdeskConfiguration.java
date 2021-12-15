package com.example.helpdesk;

import com.example.helpdesk.dto.NoteDTO;
import com.example.helpdesk.dto.TicketDTO;
import com.example.helpdesk.entity.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelpdeskConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        setUpTicketToDTO(modelMapper);
        setUpDTOtoTicket(modelMapper);
        setUpNoteToDto(modelMapper);
        setUpDtoToNote(modelMapper);
        return modelMapper;
    }

    @Bean
    public Converter<Integer, Client> intToClient(){
        return context -> {
            Client client = new Client();
            client.setId(context.getSource());
            return client;
        };
    }

    @Bean
    public Converter<Integer, Employee> intToEmployee(){
        return context -> {
            Employee employee = new Employee();
            employee.setId(context.getSource());
            return employee;
        };
    }

    @Bean
    public Converter<Integer, Status> intToStatus(){
        return context -> {
            Status status = new Status();
            status.setId(context.getSource());
            return status;
        };
    }

    @Bean
    public Converter<Client, Integer> clientToInt (){
        return context -> context.getSource().getId();
    }

    @Bean
    public Converter<Employee, Integer> employeeToInt (){
        return context -> context.getSource().getId();
    }

    @Bean
    public Converter<Status, Integer> statusToInt (){
        return context -> context.getSource().getId();
    }

    @Bean
    public Converter<Ticket, Integer> ticketToInt(){
        return context -> context.getSource().getId();
    }

    @Bean
    public Converter<Integer, Ticket> intToTicket(){
        return context -> {
            Ticket ticket = new Ticket();
            ticket.setId(context.getSource());
            return ticket;
        };
    }

    private void setUpTicketToDTO (ModelMapper modelMapper){
        modelMapper.createTypeMap(Ticket.class, TicketDTO.class)
                .addMappings(mapper -> mapper.using(clientToInt()).map(Ticket::getClient, TicketDTO::setClient))
                .addMappings(mapper -> mapper.using(employeeToInt()).map(Ticket::getOwner, TicketDTO::setOwner))
                .addMappings(mapper -> mapper.using(employeeToInt()).map(Ticket::getCreator, TicketDTO::setCreator))
                .addMappings(mapper -> mapper.using(statusToInt()).map(Ticket::getStatus, TicketDTO::setStatus));
    }

    private void setUpDTOtoTicket (ModelMapper modelMapper){
        modelMapper.createTypeMap(TicketDTO.class, Ticket.class)
                .addMappings(mapper -> mapper.using(intToClient()).map(TicketDTO::getClient, Ticket::setClient))
                .addMappings(mapper -> mapper.using(intToEmployee()).map(TicketDTO::getCreator, Ticket::setCreator))
                .addMappings(mapper -> mapper.using(intToEmployee()).map(TicketDTO::getOwner, Ticket::setOwner))
                .addMappings(mapper -> mapper.using(intToStatus()).map(TicketDTO::getStatus, Ticket::setStatus));
    }

    private void setUpNoteToDto(ModelMapper modelMapper){
        modelMapper.createTypeMap(Note.class, NoteDTO.class)
                .addMappings(mapper -> mapper.using(ticketToInt()).map(Note::getTicket, NoteDTO::setTicket))
                .addMappings(mapper -> mapper.using(employeeToInt()).map(Note::getCreator, NoteDTO::setCreator))
                .addMappings(mapper -> mapper.using(employeeToInt()).map(Note::getOwner, NoteDTO::setOwner))
                .addMappings(mapper -> mapper.using(statusToInt()).map(Note::getStatus, NoteDTO::setStatus));
    }

    private void setUpDtoToNote(ModelMapper modelMapper){
        modelMapper.createTypeMap(NoteDTO.class, Note.class)
                .addMappings(mapper -> mapper.using(intToTicket()).map(NoteDTO::getTicket, Note::setTicket))
                .addMappings(mapper -> mapper.using(intToEmployee()).map(NoteDTO::getOwner, Note::setOwner))
                .addMappings(mapper -> mapper.using(intToEmployee()).map(NoteDTO::getCreator, Note::setCreator))
                .addMappings(mapper -> mapper.using(intToStatus()).map(NoteDTO::getStatus, Note::setStatus));
    }

}
