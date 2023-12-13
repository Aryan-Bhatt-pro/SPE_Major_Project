package com.example.many_notes.SPE_Project.controller;

import com.example.many_notes.SPE_Project.entity.Note;
import com.example.many_notes.SPE_Project.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class NoteController {
    @Autowired
    private NoteService service;

    @CrossOrigin
    @PostMapping("/api/addnote")
    @CrossOrigin
    public Note addNote(@RequestBody Note new_note){
        return service.saveNote(new_note);
    }

    @CrossOrigin
    @GetMapping("/api/getnotes")
    @CrossOrigin
    public List<Note> getNotes(){
        return service.getNotes();
    }

    @PutMapping("/api/updatenote/{id}")
    @CrossOrigin
    public Note updateNote(@RequestBody Note note, @PathVariable String id){
        return service.updateNote(note, id);
    }

    @CrossOrigin
    @DeleteMapping("/api/deletenote/{id}")
    @CrossOrigin
    public String deleteNote(@PathVariable String id){
        return service.delNote(id);
    }
}
