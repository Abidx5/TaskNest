package com.crack.taskNest.controller;

import com.crack.taskNest.entity.Task;
import com.crack.taskNest.entity.Category;
import com.crack.taskNest.services.TaskService;
import com.crack.taskNest.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final CategoryService categoryService;  // Added CategoryService

    public TaskController(TaskService taskService, CategoryService categoryService) {
        this.taskService = taskService;
        this.categoryService = categoryService;
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        List<Category> categories = categoryService.getAllCategories(); // Fetch categories

        model.addAttribute("tasks", tasks);
        model.addAttribute("categories", categories); // Send categories to view
        return "tasks";
    }

    @PostMapping("/tasks")
    public String createTask(@RequestParam String title, @RequestParam String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        if (category == null) {
            category = new Category();
            category.setName(categoryName);
            categoryService.saveCategory(category);
        }
        taskService.createTask(title, category);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/category/{name}")
    public String filterTasksByCategory(@PathVariable String name, Model model) {
        Category category = categoryService.getCategoryByName(name);
        List<Task> tasks = category != null ? taskService.getTasksByCategory(category) : List.of();

        model.addAttribute("tasks", tasks);
        model.addAttribute("categories", categoryService.getAllCategories()); // Keep category list
        return "tasks";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        taskService.toggleTask(id);
        return "redirect:/tasks";
    }
}
