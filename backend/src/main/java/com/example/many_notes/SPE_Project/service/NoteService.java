package com.example.many_notes.SPE_Project.service;

import com.example.many_notes.SPE_Project.entity.Note;
import com.example.many_notes.SPE_Project.repository.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteRepo repo;


    public Note saveNote(Note note){
        return repo.save(note);
    }

    public List<Note> getNotes(){
        return repo.findAll();
    }

    public String delNote(String id){
        repo.deleteByIdCustom(id);
        return "Note Deleted";
    }

    public Note updateNote(Note note, String id){
        Note existing_note = repo.findByIdCustom(id);

        existing_note.setTitle(note.getTitle());
        existing_note.setContent(note.getContent());
        existing_note.setColor(note.getColor());

        return repo.save(existing_note);
    }
}
