package com.student.Students.Courses.service;

import com.student.Students.Courses.entity.Cycle;
import com.student.Students.Courses.entity.CycleDTO;
import com.student.Students.Courses.entity.CycleWithStudentsDTO;

import java.util.List;

public interface CycleService {

    //save a cycle
    Cycle saveCycle(Cycle cycle);

    //get all cycles
    public List<Cycle>getAllCycles();
    //get a cycle using cycle id
    public Cycle getCycleById(Long id);

    //upadate a cycle using id
    Cycle updateCycle(Long id,Cycle upadatedCycle);

    //get a cycle using categorie level
    public List<CycleDTO> getCyclesByCategoryLevel(String categoryLevel) throws IllegalAccessException;

    //remove cycle using cycle id
    void removeCycle(Long id);

    //get student list in a specific cycle using cycle id
    CycleWithStudentsDTO getCycleWithStudents(Long cycleId);


}
