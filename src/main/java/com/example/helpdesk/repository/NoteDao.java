package com.example.helpdesk.repository;

import com.example.helpdesk.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteDao extends JpaRepository<Note, Integer> {
}
