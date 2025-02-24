package com.crack.taskNest.services;

import com.crack.taskNest.entity.Category;
import com.crack.taskNest.entity.Task;
import com.crack.taskNest.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService  {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByCategory(Category category) {
        return taskRepository.findByCategory(category);
    }

    public Task createTask(String title, Category category) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        task.setCategory(category);
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggleTask(Long id) {
      Task task = taskRepository.findById(id).
            orElseThrow(()-> new IllegalArgumentException("Invalid Task Id"));
      task.setCompleted(!task.isCompleted());
      taskRepository.save(task);
    }
}
