package com.example.helpdesk.service;

import com.example.helpdesk.entity.Note;
import com.example.helpdesk.entity.Ticket;
import com.example.helpdesk.repository.NoteDao;
import com.example.helpdesk.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteDao noteDao;
    private final TicketRepository ticketRepository;

    @Autowired
    public NoteServiceImpl(NoteDao noteDao, TicketRepository ticketRepository) {
        this.noteDao = noteDao;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Note> findAll() {
        return noteDao.findAll();
    }

    @Override
    public Optional<Note> findById(Integer noteId) {
        return noteDao.findById(noteId);
    }

    @Override
    @Transactional
    public void save(Note note) {
        Date now = new Date();
        note.setCreationTime(now);
        note.setLastModifiedTime(now);
        noteDao.save(note);
        Ticket ticket = ticketRepository.getById(note.getTicket().getId());
        ticket.setStatus(note.getStatus());
        ticket.setOwner(note.getOwner());
    }

    @Override
    public void delete(Integer noteId) {
        noteDao.deleteById(noteId);
    }

    @Override
    public List<Note> getByTicketId(Integer ticketId) {
        return noteDao.getAllByTicketId(ticketId);
    }
}
