package com.challenge.api.service;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImplementation;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeServiceLayer {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeServiceLayer() {
        EmployeeImplementation employee1 = new EmployeeImplementation();
        employee1.setUuid(UUID.randomUUID());
        employee1.setFirstName("Ronald");
        employee1.setLastName("Davidson");
        employee1.setFullName("Ronald Davidson");
        employee1.setSalary(115000);
        employee1.setAge(30);
        employee1.setJobTitle("Associate Software Engineer");
        employee1.setEmail("ronald.davidson@rq.com");
        employee1.setContractHireDate(Instant.parse("2023-01-15T17:00:00Z"));

        EmployeeImplementation employee2 = new EmployeeImplementation();
        employee2.setUuid(UUID.randomUUID());
        employee2.setFirstName("Maria");
        employee2.setLastName("Sanchez");
        employee2.setFullName("Maria Sanchez");
        employee2.setSalary(85000);
        employee2.setAge(23);
        employee2.setJobTitle("Associate Product Manager");
        employee2.setEmail("maria.sanchez@rq.com");
        employee2.setContractHireDate(Instant.parse("2022-06-01T17:00:00Z"));

        EmployeeImplementation employee3 = new EmployeeImplementation();
        employee3.setUuid(UUID.randomUUID());
        employee3.setFirstName("Boberto");
        employee3.setLastName("Smith");
        employee3.setFullName("Boberto Smith");
        employee3.setSalary(200000);
        employee3.setAge(35);
        employee3.setJobTitle("Director of Software Engineering");
        employee3.setEmail("boberto.smith@rq.com");
        employee3.setContractHireDate(Instant.parse("2021-09-10T17:00:00Z"));

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        return employees.stream()
                .filter(employee -> employee.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    public Employee createEmployee(EmployeeImplementation newEmployee) {
        newEmployee.setUuid(UUID.randomUUID());
        employees.add(newEmployee);
        return newEmployee;
    }
}
