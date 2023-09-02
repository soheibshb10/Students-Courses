package com.student.Students.Courses.repository;

import com.student.Students.Courses.entity.Category;
import com.student.Students.Courses.entity.Cycle;
import com.student.Students.Courses.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CycleRepository extends JpaRepository<Cycle,Long> {
    @Query("SELECT c FROM Cycle c JOIN c.category cat WHERE cat.level = :categoryLevel")
    List<Cycle>findCyclesByCategoryLevel(@Param("categoryLevel")String categoryLevel);
    @Query("SELECT DISTINCT s FROM Cycle c JOIN c.students s WHERE c.id = :cycleId")
    List<Student> findStudentsByCycleId(Long cycleId);

    List<Cycle> findByCategory(Category category);
}
