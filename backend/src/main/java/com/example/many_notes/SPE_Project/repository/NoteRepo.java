package com.example.many_notes.SPE_Project.repository;

import com.example.many_notes.SPE_Project.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoteRepo extends JpaRepository<Note, Long>{
}
