package com.workflow.services;

import com.workflow.entities.Employee;
import com.workflow.repositories.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpService {
    @Autowired
    private EmpRepo empRepo;


    public Employee save(Employee employee) {
        return empRepo.save(employee);
    }

    public void delete(Long id) {
        empRepo.deleteById(id);
    }

    public List<Employee> getEmps() {
        return empRepo.findAll();
    }
    @Transactional
    public Employee update(Employee employee) {
        Employee emp = empRepo.getReferenceById(employee.getId());
        if (!employee.getDepartment().equals(emp.getDepartment())) emp.setDepartment(employee.getDepartment());
        if (!employee.getRole().equals(emp.getRole())) emp.setRole(employee.getRole());
        if (!employee.getName().equals(emp.getName())) emp.setName(employee.getName());
        if (employee.getStatus()!=emp.getStatus()) emp.setStatus(employee.getStatus());
        if (!employee.getEmail().equals(emp.getEmail())) emp.setEmail(employee.getEmail());
        return empRepo.save(emp);
    }
}
