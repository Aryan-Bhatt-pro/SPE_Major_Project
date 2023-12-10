package com.example.many_notes.SPE_Project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Note_Table")
public class Note {


//    @GeneratedValue
    @Id
    private String id;

    private String title;
    private String content;
    private String color;
    private String priority;
}
