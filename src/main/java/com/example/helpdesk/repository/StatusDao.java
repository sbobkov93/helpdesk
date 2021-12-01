package com.example.helpdesk.repository;

import com.example.helpdesk.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusDao extends JpaRepository<Status, Integer> {
}
