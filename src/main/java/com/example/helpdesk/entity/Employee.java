package com.example.helpdesk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeSequence")
    @SequenceGenerator(name = "employeeSequence", sequenceName = "employee_sequence", allocationSize = 1)
    private int id;
    @Column(name = "first_name", nullable = false, length = 32)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 64)
    private String lastName;
    @Column(name = "patronymic", length = 64)
    private String patronymic;
    @OneToOne
    @JoinColumn(name = "auth_id")
    private AuthenticationData authenticationData;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "details_id")
    private EmployeeDetails employeeDetails;

    @Transient
    private String fullName;

    public String getFullName() {
        if (fullName == null)
            fullName = String.format("%s %s %s", lastName, firstName, patronymic);
        return fullName;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", lastName, firstName, patronymic);
    }
}
