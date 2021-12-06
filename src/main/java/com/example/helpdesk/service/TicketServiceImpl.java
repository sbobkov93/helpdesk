package com.example.helpdesk.service;

import com.example.helpdesk.entity.Ticket;
import com.example.helpdesk.repository.TicketDao;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public Ticket getById(Integer ticketId) {
        return ticketDao.getById(ticketId);
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
