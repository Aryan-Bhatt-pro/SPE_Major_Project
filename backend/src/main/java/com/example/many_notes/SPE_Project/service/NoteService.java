package com.example.many_notes.SPE_Project.service;

import com.example.many_notes.SPE_Project.controller.NoteController;
import com.example.many_notes.SPE_Project.entity.Note;
import com.example.many_notes.SPE_Project.repository.NoteRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private static final Logger logger = LogManager.getLogger(NoteService.class);

    @Autowired
    private NoteRepo repo;


    public Note saveNote(Note note){
        logger.debug(String.format(
                "Note Created : id: %d, title: %s, content: %s, color: %s, priority: %s\n",
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getColor(),
                note.getPriority()
                )
        );
        return repo.save(note);
    }

    public List<Note> getNotes(){
        List<Note> notes = repo.findAll();
        for (Note note: notes) {
            logger.debug(String.format(
                            "Note: id: %d, title: %s, content: %s, color: %s, priority: %s\n",
                            note.getId(),
                            note.getTitle(),
                            note.getContent(),
                            note.getColor(),
                            note.getPriority()
                    )
            );
        }
        return notes;
    }

    public String delNote(Long id){
        Note note = repo.findById(id).orElseThrow(null);
        logger.debug(String.format(
                        "Note Deleted: id: %d, title: %s, content: %s, color: %s, priority: %s\n",
                        note.getId(),
                        note.getTitle(),
                        note.getContent(),
                        note.getColor(),
                        note.getPriority()
                )
        );
        repo.delete(note);
        return "Note Deleted";
    }

    public Note updateNote(Note note, Long id){
        Note existing_note = repo.findById(id).orElseThrow(null);

        existing_note.setTitle(note.getTitle());
        existing_note.setContent(note.getContent());
        existing_note.setColor(note.getColor());
        existing_note.setPriority(note.getPriority());

        logger.debug(String.format(
                        "Updated to Note: id: %d, title: %s, content: %s, color: %s, priority: %s\n",
                        note.getId(),
                        note.getTitle(),
                        note.getContent(),
                        note.getColor(),
                        note.getPriority()
                )
        );
        return repo.save(existing_note);
    }
}
