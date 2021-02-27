package com.projektseminarmicroservices.departmentservice.controller;

import com.projektseminarmicroservices.departmentservice.model.Department;
import com.projektseminarmicroservices.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/save")
    public Department saveDepartment(@RequestBody Department department){
        log.info("Inside saveDepartment() method of DepartmentController");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable Long id){
        log.info("Inside findDepartmentById() method of DepartmentController");
        Department department = departmentService.findDepartmentById(id);
        return department;
    }
}
