package com.example.helpdesk.entity;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientSequence")
    @SequenceGenerator(name="clientSequence", sequenceName="client_sequence", allocationSize = 1)
    private Integer id;
    @Column(name = "prefix", nullable = false, unique = true)
    private Integer prefix;
    @Column(name = "short_name", nullable = false, length = 16, unique = true)
    private String shortName;
    @Column(name = "name", nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrefix() {
        return prefix;
    }

    public void setPrefix(Integer prefix) {
        this.prefix = prefix;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s %s", prefix, shortName);
    }
}
