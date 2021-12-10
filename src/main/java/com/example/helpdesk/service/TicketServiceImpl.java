package com.example.helpdesk.service;

import com.example.helpdesk.entity.Ticket;
import com.example.helpdesk.repository.TicketDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{

    private TicketDao ticketDao;

    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketDao.findAll();
    }

    @Override
    public Optional<Ticket> findById(Integer ticketId) {
        return ticketDao.findById(ticketId);
    }

    @Override
    public void save(Ticket ticket) {
        Date now = new Date();
        ticket.setCreationTime(now);
        ticket.setLastModified(now);
        ticketDao.save(ticket);
    }

    @Override
    public void delete(Integer ticketId) {
        ticketDao.deleteById(ticketId);
    }
}
