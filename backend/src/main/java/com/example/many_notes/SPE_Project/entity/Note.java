package com.example.many_notes.SPE_Project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Note_Table")
public class Note {

    @Id
    @GeneratedValue
    private int id;
    @Getter
    @ManyToOne
    private User user;

    private String title;
    private String content;
    private String color;
    private String priority;
}
