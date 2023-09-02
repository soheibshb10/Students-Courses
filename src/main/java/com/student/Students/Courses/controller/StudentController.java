package com.student.Students.Courses.controller;


import com.student.Students.Courses.entity.Cycle;
import com.student.Students.Courses.entity.Student;
import com.student.Students.Courses.exceptions.StudentNotFoundException;
import com.student.Students.Courses.service.CycleService;
import com.student.Students.Courses.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;
    private CycleService cycleService;

    @Autowired
    public StudentController(StudentService studentService, CycleService cycleService) {
        this.studentService = studentService;
        this.cycleService=cycleService;
    }

    @GetMapping("/list")
    public List<Student> listStudents() {

        List<Student> students = studentService.getStudents();
        try {
            if (students != null) {
                return students;
            } else {
                throw new StudentNotFoundException("Student list is empty");
            }
        }catch (Exception e){
            throw new StudentNotFoundException("Invalid Request");
        }

    }

    @GetMapping("/view/{studentId}")
    public Student viewStudent(@PathVariable Long studentId){
        try {
            return studentService.getStudenteById(studentId);
        }catch (Exception exception){
            throw new StudentNotFoundException("Not found "+studentId );
        }

    }
    @PostMapping("/save")
    public ResponseEntity<String> createStudent(@RequestBody Map<String, Object> requestData){
       try {
            Map<String, Object> studentData = (Map<String, Object>) requestData.get("student");
            Map<String, Object> cycleData = (Map<String, Object>) requestData.get("cycle");

            Student student = new Student();

            student.setFirstname((String) studentData.get("firstname"));
            student.setLastname((String) studentData.get("lastname"));

            Cycle cycle = new Cycle();



            Long cycleId = Long.parseLong(cycleData.get("id").toString());
            Cycle cycleExist = cycleService.getCycleById(cycleId);
            if (cycleExist !=null) {
                student.getCycles().add(cycleExist);
                studentService.saveStudent(student);
                return ResponseEntity.ok("Student added succefully");
            }
            else
                throw new StudentNotFoundException("Not found this cycle" );  // Return 404 if student not found
       }
       catch (Exception exception){
           return ResponseEntity.badRequest().build();
    }

    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable String studentId, @RequestBody Student updatedstudent) {

        Student result = studentService.updateStudent(Long.parseLong(studentId), updatedstudent);

        try {

        if (result != null)
            return ResponseEntity.ok("Student modified succefully ");  // Return updated student if successful
        else
            throw new StudentNotFoundException("employee id not foumd ");  // Return 404 if student not found

        }catch (Exception exception) {
            throw new StudentNotFoundException("An error occurred while updating student with ID: " + studentId);  // Return 404 if student not found
        }


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        try {

            studentService.removeStudent(Long.parseLong(id));
            return ResponseEntity.ok("Student deleted succefully");

        }catch (Exception ex){
            throw new StudentNotFoundException(" Student not found " + id);
        }
    }


}

