package com.student.Students.Courses.controller;

import com.student.Students.Courses.entity.StudentErrorResponce;
import com.student.Students.Courses.exceptions.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class StudentHandler {

    //To handele students api errors
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponce> handlleException(StudentNotFoundException exc) {
        StudentErrorResponce error = new StudentErrorResponce();

        error.setStatus(HttpStatus.NOT_FOUND.value());

        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<StudentErrorResponce>(error, HttpStatus.NOT_FOUND);

    }





}
