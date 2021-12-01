package com.example.helpdesk.service;

import com.example.helpdesk.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClientService {

    List<Client> findAll();

    Client getById(Integer clientId);

    void save (Client client);

}
