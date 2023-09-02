package com.student.Students.Courses.service;

import com.student.Students.Courses.entity.Cycle;
import com.student.Students.Courses.entity.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);

    public List<Student>getStudents();
    public Student getStudenteById(Long id);
      public Student updateStudent(Long studentId, Student updatedStudent);

    void removeStudent(Long id);

}
