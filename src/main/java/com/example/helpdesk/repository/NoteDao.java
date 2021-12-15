package com.example.helpdesk.repository;

import com.example.helpdesk.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteDao extends JpaRepository<Note, Integer> {

//    @Query("select n from Note n where n.ticket.id = ?1")
    List<Note> getAllByTicketId(int ticketId);


}
