package com.student.Students.Courses.entity;


import jakarta.persistence.*;
import lombok.Data;



//Categorie class
@Entity
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO )
    private Long id;

    @Column
    private String level;



}
