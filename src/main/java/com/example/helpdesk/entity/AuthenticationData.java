package com.example.helpdesk.entity;

import javax.persistence.*;

@Entity
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
