package com.projektseminarmicroservices.departmentservice.service;

import com.projektseminarmicroservices.departmentservice.model.Department;
import com.projektseminarmicroservices.departmentservice.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional
    public Department saveDepartment(Department department) {
        log.info("Inside saveDepartment() method of DepartmentService");
        Department newDepartment = new Department();
        newDepartment.setDepartmentName(department.getDepartmentName());
        return departmentRepository.save(newDepartment);
    }

    @Transactional(readOnly = true)
    public Department findDepartmentById(Long departmentId) {
        log.info("Inside findDepartmentById() method of DepartmentService");
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent())
            return department.get();
        else
            return null;

    }
}
