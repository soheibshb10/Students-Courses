package com.student.Students.Courses.controller;


import ch.qos.logback.core.encoder.EchoEncoder;
import com.student.Students.Courses.entity.Category;
import com.student.Students.Courses.entity.Cycle;
import com.student.Students.Courses.entity.CycleDTO;
import com.student.Students.Courses.entity.CycleWithStudentsDTO;
import com.student.Students.Courses.exceptions.CycleNotFoundException;
import com.student.Students.Courses.exceptions.StudentNotFoundException;
import com.student.Students.Courses.service.CategoryService;
import com.student.Students.Courses.service.CycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cycles")

//To handele cycles api errors
public class CycleController {

    private CycleService cycleService;
    private CategoryService categoryService;

    @Autowired
    public CycleController(CycleService cycleService,CategoryService categoryService) {
        this.cycleService = cycleService;
        this.categoryService=categoryService;
    }

    @GetMapping("/list")
    public List<Cycle> listCycles() {
        try {

            return cycleService.getAllCycles();
        }catch (Exception exception){
            throw new CycleNotFoundException(exception.getMessage());
        }
    }

    @GetMapping("/view/{cycletId}")
    public Cycle viewCycle(@PathVariable String cycletId) {
        try {
            if (cycletId != null) {
                Cycle cycle = cycleService.getCycleById(Long.parseLong(cycletId));
                if (cycle != null)
                    return cycle;
                else
                    throw new StudentNotFoundException("Cycle not found with id " + cycletId);
            } else {
                throw new StudentNotFoundException("No valid request");
            }
        } catch (Exception ex) {
            throw new StudentNotFoundException("No valid request or invalid cycle ID format");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> createCycle(@RequestBody Map<String,Object> requestData){
//
     try {
         Map<String, Object> cycletData = (Map<String, Object>) requestData.get("cycle");
         Map<String,Object> categorieData=(Map<String,Object>) requestData.get("category");
         Cycle cycle=new Cycle();
         Category category=new Category();
         cycle.setTitle((String) cycletData.get("title"));
         cycle.setDescription((String)cycletData.get("description") );
         cycle.setDate(LocalDate.parse((String) cycletData.get("date")) );

         Long categoryId=Long.parseLong(categorieData.get("id").toString());
         Category categoryExist=categoryService.getCategoriById(categoryId);
         if(categoryExist !=null) {
             cycle.setCategory(categoryExist);
             cycleService.saveCycle(cycle);

             return ResponseEntity.ok("Cycle added succefully");
         }else
             throw new StudentNotFoundException(" categorie is not exist ");
     }catch (Exception exception){
         throw new StudentNotFoundException("Invalid Reqeust ");
     }

    }

    @PutMapping("/update/{cycleId}")
    public ResponseEntity<Cycle> updateCycle(@PathVariable String cycleId, @RequestBody Cycle updatecycle) {

        Cycle result = cycleService.updateCycle(Long.parseLong(cycleId),updatecycle);

        if (result != null) {
            return ResponseEntity.ok(result);  // Return updated student if successful
        }
        throw new StudentNotFoundException("No valid request " ); // Return 404 if student not found

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCycle(@PathVariable String id) {
        try {
            if(id !=null) {
                cycleService.removeCycle(Long.parseLong(id));
                return ResponseEntity.ok("Student deleted succefully");
            }else {
               return ResponseEntity.badRequest().build();
            }

        }catch (Exception exception){
            throw new StudentNotFoundException("No valid request or the cycle id not exist" );
        }
    }


    @GetMapping("/cyclesByCategoryLevel")
    public ResponseEntity<List<CycleDTO>> getCyclesByCategoryLevel(@RequestParam String categoryLevel) {
        try {
            List<CycleDTO> cycleDTOs = cycleService.getCyclesByCategoryLevel(categoryLevel);
            if(cycleDTOs.isEmpty())
                throw new StudentNotFoundException("Not Found this cycle");

            return ResponseEntity.ok(cycleDTOs);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // Handle the error
        }
    }

    @GetMapping("/cycleWithStudents/{cycleId}")
    public ResponseEntity<CycleWithStudentsDTO> getCycleWithStudents(@PathVariable String cycleId){

        try {
            CycleWithStudentsDTO cycleWithStudentsDTO = cycleService.getCycleWithStudents(Long.parseLong(cycleId));
            if (cycleWithStudentsDTO == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(cycleWithStudentsDTO);
        }catch (Exception e){
            throw new StudentNotFoundException("No valid request or the cycle id not exist" );

        }

    }



}



