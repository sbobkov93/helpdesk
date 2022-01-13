package com.example.helpdesk.repository;

import com.example.helpdesk.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientDao extends JpaRepository<Client, Integer> {

    Optional<Client> findByPrefix (Integer prefix);
    Optional<Client> findByShortName (String shortName);
    Optional<Client> findByName (String name);

}
