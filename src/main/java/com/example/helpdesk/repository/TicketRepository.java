package com.example.helpdesk.repository;

import com.example.helpdesk.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("select t from Ticket t left join fetch t.notes where t.id = ?1")
    Optional<Ticket> findByIdWithNotes (Integer ticketId);

}
