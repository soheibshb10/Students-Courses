package com.student.Students.Courses.exceptions;

public class CycleNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private Object fieldValue;


    public CycleNotFoundException(String resourceName) {
        super(String.format(resourceName));

    }



    }
