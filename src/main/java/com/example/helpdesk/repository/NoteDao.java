package com.example.helpdesk.repository;

import com.example.helpdesk.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteDao extends JpaRepository<Note, Integer> {

    List<Note> getAllByTicketId(int ticketId);

}
