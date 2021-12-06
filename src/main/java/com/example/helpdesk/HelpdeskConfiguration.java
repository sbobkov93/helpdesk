package com.example.helpdesk;

import com.example.helpdesk.dto.TicketDTO;
import com.example.helpdesk.entity.Client;
import com.example.helpdesk.entity.Employee;
import com.example.helpdesk.entity.Status;
import com.example.helpdesk.entity.Ticket;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelpdeskConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        Converter<Integer, Client> intToClient = context -> {
            Client client = new Client();
            client.setId(context.getSource());
            return client;
        };
        Converter<Integer, Employee> intToEmployee = context -> {
            Employee employee = new Employee();
            employee.setId(context.getSource());
            return employee;
        };
        Converter<Integer, Status> intToStatus = context -> {
            Status status = new Status();
            status.setId(context.getSource());
            return status;
        };
        modelMapper.createTypeMap(TicketDTO.class, Ticket.class)
                .addMappings(mapper -> mapper.using(intToClient).map(TicketDTO::getClient, Ticket::setClient))
                .addMappings(mapper -> mapper.using(intToEmployee).map(TicketDTO::getCreator, Ticket::setCreator))
                .addMappings(mapper -> mapper.using(intToEmployee).map(TicketDTO::getOwner, Ticket::setOwner))
                .addMappings(mapper -> mapper.using(intToStatus).map(TicketDTO::getStatus, Ticket::setStatus));
        return modelMapper;
    }

}
