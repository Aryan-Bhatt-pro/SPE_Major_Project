package com.example.many_notes.SPE_Project.repository;

import com.example.many_notes.SPE_Project.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository<Note, Integer>{

}
