package com.workflow.controllers;
import com.workflow.entities.Task;
import com.workflow.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping
    ResponseEntity<List<Task>> getTasks(){
        List<Task> tasks = taskService.getTasks();
        if (tasks!=null && !tasks.isEmpty()){
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    ResponseEntity<?> addTask(@RequestBody Task task){
        if (task!=null){
            Task save = taskService.save(task);
            if (save!=null) return ResponseEntity.ok(save);
        }
        return ResponseEntity.badRequest().body("Task not added");
    }

    @PutMapping
    public ResponseEntity<?> updateTask(@RequestBody Task task){
        if (task!=null){
            Task update = taskService.update(task);
            if (update!=null)  return ResponseEntity.ok(update);
        }
        return ResponseEntity.badRequest().body("Updation failed");
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id){
        if (id!=null){
            taskService.delete(id);
        }
    }
}
