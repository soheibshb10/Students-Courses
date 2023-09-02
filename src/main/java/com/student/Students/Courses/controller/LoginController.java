package com.student.Students.Courses.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {
    //redirect to the login page
    @GetMapping("/login")
    String login() {
        return "login";
    }
}
