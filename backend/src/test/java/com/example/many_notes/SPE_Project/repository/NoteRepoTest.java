package com.example.many_notes.SPE_Project.repository;

import com.example.many_notes.SPE_Project.entity.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.not;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class NoteRepoTest {

    @Autowired
    private NoteRepo noteRepo;

    @BeforeEach
    void setUp() {
        Note note = new Note(
                "1",
                "Gay",
                "Tera baap Gay",
                "Blue",
                "High"
        );
        noteRepo.save(note);
    }
    @Test
    void findByIdCustom() {
        Boolean exists = noteRepo.existsById("1");
        assertThat(exists).isTrue();
    }

    @Test
    void deleteByIdCustom() {
        noteRepo.deleteById("1");
        Boolean exists = noteRepo.existsById("1");
        assertThat(exists).isFalse();
    }

    @Test
    void updateNoteById() {
        Note note = noteRepo.getReferenceById("1");
        note.setTitle("This is a new Title");
        noteRepo.save(note);

        Note updatedNote = noteRepo.getReferenceById("1");

        assertEquals(updatedNote.getTitle(), "This is a new Title");
    }
}