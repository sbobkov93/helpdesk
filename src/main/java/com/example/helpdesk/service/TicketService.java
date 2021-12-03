package com.example.helpdesk.service;

import com.example.helpdesk.entity.Employee;
import com.example.helpdesk.entity.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> findAll();

    Ticket getById(Integer ticketId);

    void save (Ticket ticket);

    void delete (Integer ticketId);
}
