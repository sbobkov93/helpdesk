package com.example.helpdesk.service;

import com.example.helpdesk.entity.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    List<Note> findAll();

    Optional<Note> findById(Integer noteId);

    void save (Note note);

    void delete (Integer noteId);

    List<Note> getByTicketId(Integer ticketId);

}
