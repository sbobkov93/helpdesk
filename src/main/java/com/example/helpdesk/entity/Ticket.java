package com.example.helpdesk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ticket")
public class Ticket {

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
    @OneToOne()
    @JoinColumn(name = "creator_id", nullable = false)
    private Employee creator;
    @OneToOne()
    @JoinColumn(name = "owner_id", nullable = false)
    private Employee owner;
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    @Column(name = "last_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @OneToOne()
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;
    @OneToMany(mappedBy = "ticket")
    private List<Note> notes;
}
