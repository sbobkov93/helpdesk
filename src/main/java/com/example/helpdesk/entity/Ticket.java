package com.example.helpdesk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ticket")
public class Ticket extends HelpdeskRecord{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticketSequence")
    @SequenceGenerator(name = "ticketSequence", sequenceName = "ticket_sequence", allocationSize = 1)
    private int id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", length = 1024)
    private String description;
    @OneToOne()
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @OneToMany(mappedBy = "ticket")
    private List<Note> notes;
}
