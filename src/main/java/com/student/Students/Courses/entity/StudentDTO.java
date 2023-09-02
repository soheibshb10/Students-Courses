package com.student.Students.Courses.entity;

import lombok.Data;

import java.beans.ConstructorProperties;

@Data
public class StudentDTO {
    private String firstname;

    private String lastname;


    public StudentDTO(String firstname, String lastname) {
        this.firstname=firstname;
        this.lastname=lastname;
    }

}
