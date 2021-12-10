package com.example.helpdesk.service;

import com.example.helpdesk.entity.Employee;
import com.example.helpdesk.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    List<Ticket> findAll();

    Optional<Ticket> findById(Integer ticketId);

    void save (Ticket ticket);

    void delete (Integer ticketId);
}
