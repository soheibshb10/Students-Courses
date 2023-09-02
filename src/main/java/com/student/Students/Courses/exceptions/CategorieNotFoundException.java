package com.student.Students.Courses.exceptions;

public class CategorieNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private Object fieldValue;


    public CategorieNotFoundException(String resourceName) {
        super(String.format(resourceName));

    }



}
