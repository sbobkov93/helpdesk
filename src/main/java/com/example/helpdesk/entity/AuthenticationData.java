package com.example.helpdesk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "authentication_data")
public class AuthenticationData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authDataSequence")
    @SequenceGenerator(name = "authDataSequence", sequenceName = "auth_data_sequence", allocationSize = 1)
    private Integer id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
