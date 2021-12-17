package com.example.helpdesk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statusSequence")
    @SequenceGenerator(name = "statusSequence", sequenceName = "status_sequence", allocationSize = 1)
    private int id;
    @Column(name = "status", unique = true, nullable = false, length = 32)
    private String status;
}
