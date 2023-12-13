package com.example.many_notes.SPE_Project.controller;

import com.example.many_notes.SPE_Project.entity.Note;
import com.example.many_notes.SPE_Project.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class NoteController {

    private static final Logger logger = LogManager.getLogger(NoteController.class);

    @Autowired
    private NoteService service;

    @PostMapping("/api/addnote")
    @CrossOrigin
    public Note addNote(@RequestBody Note new_note){
        logger.debug("Adding Note ... ");
        return service.saveNote(new_note);
    }

    @GetMapping("/api/getnotes")
    @CrossOrigin
    public List<Note> getNotes(){
        logger.debug("Getting Note ... ");
        return service.getNotes();
    }

    @PutMapping("/api/updatenote/{id}")
    @CrossOrigin
    public Note updateNote(@RequestBody Note note, @PathVariable Long id){
        logger.debug("Updating Node ... ");
        return service.updateNote(note, id);
    }

    @DeleteMapping("/api/deletenote/{id}")
    @CrossOrigin
    public String deleteNote(@PathVariable Long id){
        logger.debug("Deleted Note ... ");
        return service.delNote(id);
    }
}
