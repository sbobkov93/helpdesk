package com.example.helpdesk.service;

import com.example.helpdesk.entity.Ticket;
import com.example.helpdesk.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{

    private TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> findById(Integer ticketId) {
        return ticketRepository.findById(ticketId);
    }

    @Override
    public void save(Ticket ticket) {
        Date now = new Date();
        ticket.setCreationTime(now);
        ticket.setLastModified(now);
        ticketRepository.save(ticket);
    }

    @Override
    public void delete(Integer ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    @Override
    public Optional<Ticket> findByIdWithNotes(Integer ticketId) {
        return ticketRepository.findByIdWithNotes(ticketId);
    }

    @Override
    public boolean isExistInDb(Integer ticketId) {
        return findById(ticketId).isPresent();
    }
}
