package com.example.helpdesk.service;

import com.example.helpdesk.entity.Status;
import com.example.helpdesk.entity.Ticket;

import java.util.List;

public interface StatusService {

    List<Status> findAll();

    Status getById(Integer statusId);

    void save (Status status);

    void delete (Integer statusId);

}
