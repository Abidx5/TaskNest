package com.crack.taskNest.repository;

import com.crack.taskNest.entity.Category;
import com.crack.taskNest.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByCategory(Category category);
}
