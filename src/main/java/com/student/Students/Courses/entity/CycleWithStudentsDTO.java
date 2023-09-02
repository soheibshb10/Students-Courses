package com.student.Students.Courses.entity;

import lombok.Data;

import java.util.List;

@Data
public class CycleWithStudentsDTO {

    private String  cycleTitle;

    private List<StudentDTO>students;



}
