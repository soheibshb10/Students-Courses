package com.student.Students.Courses.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CycleDTO {

    private String title;
    private String description;
    private LocalDate date;



}
