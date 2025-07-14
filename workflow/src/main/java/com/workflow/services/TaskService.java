package com.workflow.services;

import com.workflow.entities.Task;
import com.workflow.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;
    public void delete(Long id) {
        taskRepo.deleteById(id);
    }

    public Task update(Task task) {
        Task task1 = taskRepo.getReferenceById(task.getId());
        if (!task.getTitle().equals(task1.getTitle())) task1.setTitle(task.getTitle());
        if (!task.getDescription().equals(task1.getDescription())) task1.setDescription(task.getDescription());
        if (!task.getDeadline().equals(task1.getDeadline())) task1.setDeadline(task.getDeadline());
        if (!task.getStatus().equals(task1.getStatus())) task1.setStatus(task.getStatus());
        return taskRepo.save(task1);
    }

    public Task save(Task task) {
        System.out.println(task.toString());
        return taskRepo.save(task);
    }

    public List<Task> getTasks() {
        return taskRepo.findAll();
    }
}
