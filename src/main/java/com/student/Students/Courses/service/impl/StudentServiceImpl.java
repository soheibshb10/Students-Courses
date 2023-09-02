package com.student.Students.Courses.service.impl;

import com.student.Students.Courses.entity.Cycle;
import com.student.Students.Courses.entity.Student;
import com.student.Students.Courses.exceptions.StudentNotFoundException;
import com.student.Students.Courses.repository.CycleRepository;
import com.student.Students.Courses.repository.StudentRepository;
import com.student.Students.Courses.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private CycleRepository cycleRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,CycleRepository cycleRepository) {
        this.studentRepository = studentRepository;
        this.cycleRepository=cycleRepository;
    }


    @Override
    public Student saveStudent(Student student) {
        try {
            Cycle cycle = new Cycle();
            Student student1 = new Student();
            Student savedStudent = studentRepository.save(student);//save student
            return savedStudent;

        }catch (Exception exception){
            throw new StudentNotFoundException("Invalid student object ");
        }

    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudenteById(Long id) {
        Optional<Student> result=studentRepository.findById(id);
        Student student=null;
        if(result.isPresent()){
            student=result.get();
            return student;
        }else {
            throw new RuntimeException("Did not find Student id"+id);
        }
            }

    @Override
    public Student updateStudent(Long studentId, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(studentId).orElse(null);

        if (existingStudent != null) {
            // Update the existingStudent with data from updatedStudent
            existingStudent.setFirstname(updatedStudent.getFirstname());
            existingStudent.setLastname(updatedStudent.getLastname());
            // Update other fields as needed

            // Save the updated student entity
            return studentRepository.save(existingStudent);
        }

        return null; // Return null if student doesn't exist
    }
    @Override
    public void removeStudent(Long id) {
        Optional<Student>result=studentRepository.findById(id);
        if(result.isPresent())
            studentRepository.deleteById(id);
        else
            throw new RuntimeException();
    }
}
