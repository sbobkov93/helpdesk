package com.example.helpdesk.service;

import com.example.helpdesk.entity.Client;

import java.util.List;
import java.util.Optional;


public interface ClientService {

    List<Client> findAll();

    Optional<Client> findById(Integer clientId);

    void save (Client client);

    void delete (Integer clientId);

    Optional<Client> findByPrefix(Integer prefix);

    Optional<Client> findByShortName(String shortName);

    Optional<Client> findByName(String name);

}
