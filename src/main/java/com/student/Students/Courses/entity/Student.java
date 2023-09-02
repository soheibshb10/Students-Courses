package com.student.Students.Courses.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


//student class
@Entity
@Data
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO )
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @ManyToMany(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(

            name="students_cycles",

            joinColumns=@JoinColumn(name="student_id"),

            inverseJoinColumns=@JoinColumn(name="cycle_id")

    )
    private List<Cycle> cycles= new ArrayList<>(); ;



}
