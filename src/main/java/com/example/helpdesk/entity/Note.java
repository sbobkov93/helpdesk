package com.example.helpdesk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "note")
public class Note extends HelpdeskRecord{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "noteSequence")
    @SequenceGenerator(name = "noteSequence", sequenceName = "note_sequence", allocationSize = 1)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;
    @Column(name = "comment", nullable = false, length = 1024)
    private String comment;

}
