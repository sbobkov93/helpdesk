package com.example.helpdesk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "noteSequence")
    @SequenceGenerator(name = "noteSequence", sequenceName = "note_sequence", allocationSize = 1)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;
    @Column(name = "comment", nullable = false, length = 1024)
    private String comment;
    @OneToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private Employee creator;
    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Employee owner;
    @Column(name = "created_at", nullable = false)
    private Date creationTime;
    @Column(name = "last_modified", nullable = false)
    private Date lastModifiedTime;
    @OneToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

}
