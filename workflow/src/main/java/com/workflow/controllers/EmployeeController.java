package com.workflow.controllers;

import com.workflow.entities.Employee;
import com.workflow.services.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmpService empService;
    @GetMapping
    ResponseEntity<List<Employee>> getEmps(){
        List<Employee> emps = empService.getEmps();
        if (emps!=null && !emps.isEmpty()){
            return ResponseEntity.ok(emps);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    ResponseEntity<?> addEmp(@RequestBody Employee employee){
        if (employee!=null){
            Employee save = empService.save(employee);
            if (save!=null) return ResponseEntity.ok(save);
        }
        return ResponseEntity.badRequest().body("Employee not added");
    }

    @PutMapping
    public ResponseEntity<?> updateEmp(@RequestBody Employee employee){
        if (employee!=null){
            Employee update = empService.update(employee);
            if (update!=null)  return ResponseEntity.ok(update);
        }
        return ResponseEntity.badRequest().body("Updation failed");
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmp(@PathVariable Long id){
        if (id!=null){
            empService.delete(id);
        }
    }
}
