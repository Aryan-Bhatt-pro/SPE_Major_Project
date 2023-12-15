package com.example.many_notes.SPE_Project.repository;

import com.example.many_notes.SPE_Project.entity.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
                1,
                "Rudransh's Notes",
                "This is the content of notes",
                "Blue",
                "High",false
        );
        noteRepo.save(note);
    }
    @Test
    void findByIdCustom() {
        boolean exists = noteRepo.existsById((long)1);
        assertThat(exists).isTrue();
    }

    @Test
    void deleteByIdCustom() {
        noteRepo.deleteById((long)1);
        Boolean exists = noteRepo.existsById((long)1);
        assertThat(exists).isFalse();
    }

    @Test
    void updateNoteById() {
        Note note = noteRepo.getReferenceById((long)1);
        note.setTitle("This is a new Title");
        noteRepo.save(note);

        Note updatedNote = noteRepo.getReferenceById((long) 1);

        assertEquals(updatedNote.getTitle(), "This is a new Title");
    }
}