package com.example.many_notes.SPE_Project.controller;

import com.example.many_notes.SPE_Project.entity.Note;
import com.example.many_notes.SPE_Project.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {
    @Autowired
    private NoteService service;

    @CrossOrigin
    @PostMapping("/api/addnote")
    public Note addNote(@RequestBody Note new_note){
        return service.saveNote(new_note);
    }

    @CrossOrigin
    @GetMapping("/api/getnotes")
    public List<Note> getNotes(){
        return service.getNotes();
    }

    @CrossOrigin
    @PutMapping("/api/updatenote")
    public Note updateNote(@RequestBody Note note){
        return service.updateNote(note);
    }

    @CrossOrigin
    @DeleteMapping("/api/deletenote/{id}")
    public String deleteNote(@PathVariable int id){
        return service.delNote(id);
    }
}
