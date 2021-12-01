package com.example.helpdesk.repository;

import com.example.helpdesk.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client, Integer> {
}
