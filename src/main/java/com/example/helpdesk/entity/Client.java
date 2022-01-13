package com.example.helpdesk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientSequence")
    @SequenceGenerator(name="clientSequence", sequenceName="client_sequence", allocationSize = 1)
    private Integer id;
    @NotNull(message = "Обязательное поле")
    @Column(name = "prefix", nullable = false, unique = true)
    private Integer prefix;
    @NotNull(message = "Обязательное поле")
    @Size(min = 2, message = "Обязательное поле")
    @Column(name = "short_name", nullable = false, length = 16, unique = true)
    private String shortName;
    @NotNull(message = "Обязательное поле")
    @Size(min = 2, message = "Обязательное поле")
    @Column(name = "name", nullable = false)
    private String name;

    @Override
    public String toString() {
        return String.format("%s %s", prefix, shortName);
    }
}
