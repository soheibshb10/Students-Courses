package com.student.Students.Courses.controller;

import com.student.Students.Courses.entity.Category;
import com.student.Students.Courses.entity.Cycle;
import com.student.Students.Courses.exceptions.CategorieNotFoundException;
import com.student.Students.Courses.exceptions.StudentNotFoundException;
import com.student.Students.Courses.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    //to get all the categories
    @GetMapping("/list")
    public List<Category> listCycles() {
        return categoryService.getAllCategories();
    }

    //to get one categorie
    @GetMapping("/view/{categoryId}")
    public ResponseEntity<Category> viewCategory(@PathVariable String categoryId) {
        try {
            Category category = categoryService.getCategoriById(Long.parseLong(categoryId));

            if (category != null)
                return ResponseEntity.ok(category);   // return the specified categorie
            else
                throw new CategorieNotFoundException(" categorie not found  ");// Return 404 if category doesn't exist
        }
        catch(Exception exception) {
            return ResponseEntity.badRequest().build(); // Return 400 if categoryId is null
        }
    }


  //to save a categorie
    @PostMapping("/save")
    public ResponseEntity<String> createCategory(@RequestBody Category category){
//
        try {

            categoryService.saveCategory(category);

            return ResponseEntity.ok("Cycle added succefully");

        }catch (Exception exception){
            return ResponseEntity.badRequest().build(); //return 400 error if request contains errors
        }

    }

    //to develop categorie
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable String categoryId, @RequestBody Category updatecategorie) {

        Category result = categoryService.updateCategory(Long.parseLong(categoryId) ,updatecategorie);
        try {
            if (result != null)
                return ResponseEntity.ok(result);  // Return updated student if successful
            else
                throw new StudentNotFoundException("category not found ");  // Return 404 if student not found
        }catch (Exception exception){
                return ResponseEntity.badRequest().build();
            }

    }

    //to delete categorie
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategorie(@PathVariable String id) {
        try {

            categoryService.removeCategorie(Long.parseLong(id));
            return ResponseEntity.ok("Categorie deleted succefully");

        }catch (Exception exception){
            throw new StudentNotFoundException(exception.getMessage());
        }


    }





}
