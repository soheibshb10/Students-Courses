package com.student.Students.Courses.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;



//cycle class

@Entity
@Data
@Table(name = "cycles")
public class Cycle {

    @Id()
    @GeneratedValue(strategy =GenerationType.AUTO )
    private Long id;

   @Column
    private String title;

   @Column
    private String description;

   @Column
    private LocalDate date;

    @JsonIgnore
    @ManyToMany(mappedBy="cycles",fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Student> students;

@ManyToOne( cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
   @JoinColumn(name="category_id")
    private Category category;




}
