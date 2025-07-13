package com.workflow.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email", nullable = false,unique = true)
    private String email;
    @Column(name = "role")
    private String role;
    @Column(name = "department")
    private String department;
    @Column(name = "status")
    private Boolean status;
}
