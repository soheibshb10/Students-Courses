package com.student.Students.Courses.entity;


import lombok.Data;

@Data
public class CycleErrorResponce {
    private int status;
    private String message;
    private long timeStamp;
}
