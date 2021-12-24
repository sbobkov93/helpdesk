package com.example.helpdesk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class HelpdeskRecord {

    @OneToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private Employee creator;
    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Employee owner;
    @Column(name = "created_at", nullable = false)
    private Date creationTime;
    @Column(name = "last_modified", nullable = false)
    private Date lastModified;
    @OneToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

}
