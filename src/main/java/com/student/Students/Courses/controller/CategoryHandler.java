package com.student.Students.Courses.controller;

import com.student.Students.Courses.entity.CycleErrorResponce;
import com.student.Students.Courses.exceptions.CycleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CategoryHandler {

    //To handele categories api errors
    @ExceptionHandler
    public ResponseEntity<CycleErrorResponce> handlleException(CycleNotFoundException exc) {
        CycleErrorResponce error = new CycleErrorResponce();
        error.setStatus(HttpStatus.NOT_FOUND.value());

        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<CycleErrorResponce>(error, HttpStatus.NOT_FOUND);

    }


}
