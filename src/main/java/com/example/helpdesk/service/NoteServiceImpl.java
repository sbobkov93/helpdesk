package com.example.helpdesk.service;

import com.example.helpdesk.entity.Note;
import com.example.helpdesk.repository.NoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteDao noteDao;

    @Autowired
    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
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
    public void save(Note note) {
        noteDao.save(note);
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
