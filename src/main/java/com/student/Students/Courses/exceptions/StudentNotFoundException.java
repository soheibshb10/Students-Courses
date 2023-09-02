package com.student.Students.Courses.exceptions;

public class StudentNotFoundException extends RuntimeException{


    private String resourceName;
    private String fieldName;
    private Object fieldValue;





    public StudentNotFoundException(String resourceName) {
        super(String.format( resourceName));

    }
}
