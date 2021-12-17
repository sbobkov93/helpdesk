package com.example.helpdesk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "employee_details")
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeDetailsSequence")
    @SequenceGenerator(name = "employeeDetailsSequence", sequenceName = "employee_details_sequence", allocationSize = 1)
    private int id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

//    @Column(name = "registration_date")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date registrationDate;

//    private Date lastConnectionDate;

}
