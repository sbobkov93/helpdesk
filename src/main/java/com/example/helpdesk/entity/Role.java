package com.example.helpdesk.entity;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSequence")
    @SequenceGenerator(name = "roleSequence", sequenceName = "role_sequence", allocationSize = 1)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
