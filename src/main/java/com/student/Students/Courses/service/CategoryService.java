package com.student.Students.Courses.service;

import com.student.Students.Courses.entity.Category;
import com.student.Students.Courses.entity.Cycle;

import java.util.List;

public interface CategoryService {

    //to save a categorie in Database
    Category saveCategory(Category category);

    //to get categories list
    public List<Category> getAllCategories();
    //to get a categrie using categrie id
    public Category getCategoriById(Long id);

    //update specifyed categorie
    public Category  updateCategory(Long id, Category updatedCategory);

    //remove categorie using categorie id
    void removeCategorie(Long id);
}
