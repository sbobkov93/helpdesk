package com.example.helpdesk.configuration;

import com.example.helpdesk.dto.NoteDTO;
import com.example.helpdesk.dto.TicketDTO;
import com.example.helpdesk.dto.EmployeeDTO;
import com.example.helpdesk.entity.*;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ModelMapperConfiguration {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ModelMapperConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
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

    @Bean
    public Converter<String, String> encodePassword(){
        return context -> passwordEncoder.encode(context.getSource());
    }

    @Bean
    public Converter<Integer, Role> intToRole(){
        return context -> {
            Role role = new Role();
            role.setId(context.getSource());
            return role;
        };
    }

    @Bean
    public Converter<Role, Integer> roleToInt(){
        return context -> context.getSource().getId();
    }

    @Bean
    public TypeMapConfigurator DtoToTicket(){
        return modelMapper -> modelMapper.createTypeMap(TicketDTO.class, Ticket.class)
                .addMappings(mapper -> mapper.using(intToClient()).map(TicketDTO::getClient, Ticket::setClient))
                .addMappings(mapper -> mapper.using(intToEmployee()).map(TicketDTO::getCreator, Ticket::setCreator))
                .addMappings(mapper -> mapper.using(intToEmployee()).map(TicketDTO::getOwner, Ticket::setOwner))
                .addMappings(mapper -> mapper.using(intToStatus()).map(TicketDTO::getStatus, Ticket::setStatus));
    }

    @Bean
    public TypeMapConfigurator ticketToDto(){
        return modelMapper -> modelMapper.createTypeMap(Ticket.class, TicketDTO.class)
                .addMappings(mapper -> mapper.using(clientToInt()).map(Ticket::getClient, TicketDTO::setClient))
                .addMappings(mapper -> mapper.using(employeeToInt()).map(Ticket::getOwner, TicketDTO::setOwner))
                .addMappings(mapper -> mapper.using(employeeToInt()).map(Ticket::getCreator, TicketDTO::setCreator))
                .addMappings(mapper -> mapper.using(statusToInt()).map(Ticket::getStatus, TicketDTO::setStatus));
    }

    @Bean
    public TypeMapConfigurator NoteToDto (){
       return modelMapper -> modelMapper.createTypeMap(Note.class, NoteDTO.class)
                .addMappings(mapper -> mapper.using(ticketToInt()).map(Note::getTicket, NoteDTO::setTicket))
                .addMappings(mapper -> mapper.using(employeeToInt()).map(Note::getCreator, NoteDTO::setCreator))
                .addMappings(mapper -> mapper.using(employeeToInt()).map(Note::getOwner, NoteDTO::setOwner))
                .addMappings(mapper -> mapper.using(statusToInt()).map(Note::getStatus, NoteDTO::setStatus));
    }

    @Bean
    public TypeMapConfigurator dtoToNote(){
       return modelMapper ->  modelMapper.createTypeMap(NoteDTO.class, Note.class)
                .addMappings(mapper -> mapper.using(intToTicket()).map(NoteDTO::getTicket, Note::setTicket))
                .addMappings(mapper -> mapper.using(intToEmployee()).map(NoteDTO::getOwner, Note::setOwner))
                .addMappings(mapper -> mapper.using(intToEmployee()).map(NoteDTO::getCreator, Note::setCreator))
                .addMappings(mapper -> mapper.using(intToStatus()).map(NoteDTO::getStatus, Note::setStatus));
    }

    @Bean
    public TypeMapConfigurator dtoToEmployee(){
        return modelMapper -> modelMapper.createTypeMap(EmployeeDTO.class, Employee.class)
                .addMappings(mapper -> mapper.map(EmployeeDTO::getUserName,
                        (dest, v) -> dest.getAuthenticationData().setUserName((String) v)))
                .addMappings(mapper -> mapper.using(encodePassword()).map(EmployeeDTO::getPassword,
                        (dest, v) -> dest.getAuthenticationData().setPassword((String) v)))
                .addMappings(mapper -> mapper.using(intToRole()).map(EmployeeDTO::getRole,
                        (dest, v) -> dest.getAuthenticationData().setRole((Role) v)))
                .addMappings(mapper -> mapper.map(EmployeeDTO::getPhone,
                        (dest, v) -> dest.getEmployeeDetails().setPhone((String) v)))
                .addMappings(mapper -> mapper.map(EmployeeDTO::getEmail,
                        (dest, v) -> dest.getEmployeeDetails().setEmail((String) v)))
                .addMappings(mapper -> mapper.map(EmployeeDTO::getEnabled,
                        (dest, v) -> dest.getAuthenticationData().setEnabled((Boolean) v)))
                .addMappings(mapper -> mapper.map(EmployeeDTO::getDetailsId,
                        (dest, v) -> dest.getEmployeeDetails().setId((Integer) v)))
                .addMappings(mapper -> mapper.map(EmployeeDTO::getAuthenticationDataId,
                        (dest, v) -> dest.getAuthenticationData().setId((Integer) v)));
    }

    @Bean
    public TypeMapConfigurator EmployeeToDto(){
        return modelMapper -> modelMapper.createTypeMap(Employee.class, EmployeeDTO.class)
                .addMappings(mapper -> mapper.map(src -> src.getAuthenticationData().getUserName(),
                        EmployeeDTO::setUserName))
                .addMappings(mapper -> mapper.using(roleToInt()).map(src -> src.getAuthenticationData().getRole(),
                        EmployeeDTO::setRole))
                .addMappings(mapper -> mapper.map(src -> src.getAuthenticationData().getEnabled(),
                        EmployeeDTO::setEnabled))
                .addMappings(mapper -> mapper.map(src -> src.getEmployeeDetails().getEmail(), EmployeeDTO::setEmail))
                .addMappings(mapper -> mapper.map(src -> src.getEmployeeDetails().getPhone(), EmployeeDTO::setPhone))
                .addMappings(mapper -> mapper.map(src -> src.getAuthenticationData().getId(),
                        EmployeeDTO::setAuthenticationDataId))
                .addMappings(mapper -> mapper.map(src -> src.getEmployeeDetails().getId(), EmployeeDTO::setDetailsId));
    }

}
