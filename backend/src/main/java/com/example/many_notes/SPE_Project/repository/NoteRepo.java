package com.example.many_notes.SPE_Project.repository;

import com.example.many_notes.SPE_Project.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoteRepo extends JpaRepository<Note, String>{
    @Modifying
    @Query("SELECT e FROM Note e WHERE e.id = :id")
    Note findByIdCustom(@Param("id") String id);

    @Modifying
    @Query("DELETE Note e WHERE e.id = :id")
    void deleteByIdCustom(@Param("id") String id);

    @Modifying
    @Query("UPDATE Note n SET n.title = :newTitle, n.content = :newContent, n.priority = :newPriority, n.color = :newColor WHERE n.id = :id")
    void updateNoteById(
            @Param("id") String id,
            @Param("newTitle") String newTitle,
            @Param("newContent") String newContent,
            @Param("newPriority") String newPriority,
            @Param("newColor") String newColor
    );



}
