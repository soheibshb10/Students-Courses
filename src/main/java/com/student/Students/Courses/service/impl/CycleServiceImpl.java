package com.student.Students.Courses.service.impl;

import com.student.Students.Courses.entity.Cycle;
import com.student.Students.Courses.entity.CycleDTO;
import com.student.Students.Courses.entity.CycleWithStudentsDTO;
import com.student.Students.Courses.entity.StudentDTO;
import com.student.Students.Courses.exceptions.CycleNotFoundException;
import com.student.Students.Courses.repository.CycleRepository;
import com.student.Students.Courses.service.CycleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service

public class CycleServiceImpl implements CycleService {

    private CycleRepository cycleRepository;


    public CycleServiceImpl(CycleRepository cycleRepository) {

        this.cycleRepository = cycleRepository;

    }

    @Override
    public Cycle saveCycle(Cycle cycle) {
        return cycleRepository.save(cycle);

    }

    @Override
    public List<Cycle> getAllCycles() {
        try {
            return cycleRepository.findAll();
        }catch (Exception exception){
            throw new CycleNotFoundException(exception.getMessage());
        }


    }
    public Cycle getCycleById(Long id){
        Optional<Cycle>result=cycleRepository.findById(id);
        Cycle cycle=null;
        if(result.isPresent()){
            cycle=result.get();
        }else {
            return null;
        }
          return cycle;
    }

    @Override
    public Cycle updateCycle(Long id, Cycle updatedCycle) {
        Cycle existingCycle = cycleRepository.findById(id).orElse(null);

        if (existingCycle != null) {
            // Update the existingStudent with data from updatedStudent
            existingCycle.setTitle(updatedCycle.getTitle());
            existingCycle.setDescription(updatedCycle.getDescription());
            existingCycle.setDate(updatedCycle.getDate());
            existingCycle.setCategory(updatedCycle.getCategory());

            // Save the updated student entity
            return cycleRepository.save(existingCycle);
        }

        return null; // Return null if student doesn't exist
    }

    @Override
    public List<CycleDTO> getCyclesByCategoryLevel(String categoryLevel) throws IllegalAccessException {
        List<Cycle> cycles = cycleRepository.findCyclesByCategoryLevel(categoryLevel);
     if (cycles.isEmpty())
       return null;

        return cycles.stream()
                .map(cycle -> {
                    CycleDTO dto = new CycleDTO();
                    dto.setTitle(cycle.getTitle());
                    dto.setDescription(cycle.getDescription());
                    dto.setDate(cycle.getDate());
                    return dto;
                })
                .collect(Collectors.toList());



    }


    @Override
    public void removeCycle(Long id) {
        Optional<Cycle>result=cycleRepository.findById(id);
        if(result.isPresent())
            cycleRepository.deleteById(id);

    }

    @Override
    public CycleWithStudentsDTO getCycleWithStudents(Long cycleId) {
        Cycle cycle = cycleRepository.findById(cycleId).orElse(null);
        if(cycle==null)
            return null;
        CycleWithStudentsDTO cycleWithStudentsDTO=new CycleWithStudentsDTO();
        cycleWithStudentsDTO.setCycleTitle(cycle.getTitle());

        List<StudentDTO> studentDTOS=cycle.getStudents().stream()
                .map(student -> new StudentDTO(student.getFirstname(),student.getLastname()))
                .collect(Collectors.toList());
        cycleWithStudentsDTO.setStudents(studentDTOS);
        return cycleWithStudentsDTO;


    }
}
