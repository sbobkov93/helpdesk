package com.example.helpdesk.entity;

import javax.persistence.*;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statusSequence")
    @SequenceGenerator(name = "statusSequence", sequenceName = "status_sequence", allocationSize = 1)
    private int id;
    @Column(name = "status", unique = true, nullable = false, length = 32)
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
