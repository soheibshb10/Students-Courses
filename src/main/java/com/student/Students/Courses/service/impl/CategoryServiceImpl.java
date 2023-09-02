package com.student.Students.Courses.service.impl;

import com.student.Students.Courses.entity.Category;
import com.student.Students.Courses.entity.Cycle;
import com.student.Students.Courses.exceptions.CategorieNotFoundException;
import com.student.Students.Courses.repository.CategoryRepository;
import com.student.Students.Courses.repository.CycleRepository;
import com.student.Students.Courses.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CycleRepository cycleRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,CycleRepository cycleRepository) {
        this.categoryRepository = categoryRepository;
        this.cycleRepository=cycleRepository;
    }


    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoriById(Long id) {
        Optional<Category>result=categoryRepository.findById(id);
        Category category=null;
        if(result.isPresent()){
            category=result.get();
            return category;
        }
        else {
            return null;
        }

    }


    @Override
    public Category updateCategory(Long id, Category updatedCategory) {
        Category existingCategory = categoryRepository.findById(id).orElse(null);

        if (existingCategory != null) {
            // Update the existingStudent with data from updatedStudent
            existingCategory.setLevel(updatedCategory.getLevel());
            // Update other fields as needed

            // Save the updated student entity
            return categoryRepository.save(existingCategory);
        }

        return null; // Return null if student doesn't exist
    }


    @Override
    public void removeCategorie(Long categoryId) {

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();

            List<Cycle> cyclesToDelete = cycleRepository.findByCategory(category);
            for (Cycle cycle : cyclesToDelete) {
                cycle.setCategory(null);
                cycleRepository.delete(cycle);
            }

            categoryRepository.delete(category);
        } else {
            throw new CategorieNotFoundException("Category not found");
        }
    }





        }

