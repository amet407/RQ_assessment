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

    // Generate mock employees.
    // In a real application, this data would be retrieved from a database.
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

    // Retrieves all mock employees.
    // In a real application, this method would query a database for all Employee records.
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // Retrieves an Employee by their UUID.
    // If no Employee exists with the provided UUID, throws a 404 Not Found exception.
    public Employee getEmployeeByUuid(UUID uuid) {
        return employees.stream()
                .filter(employee -> employee.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    // Creates a new employee.
    // Validates that required fields are present before creating.
    public Employee createEmployee(EmployeeImplementation newEmployee) {
        if (newEmployee.getFirstName() == null
                || newEmployee.getFirstName().isBlank()
                || newEmployee.getLastName() == null
                || newEmployee.getLastName().isBlank()
                || newEmployee.getEmail() == null
                || newEmployee.getEmail().isBlank()
                || newEmployee.getJobTitle() == null
                || newEmployee.getJobTitle().isBlank()
                || newEmployee.getSalary() == null
                || newEmployee.getAge() == null
                || newEmployee.getContractHireDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required employee fields");
        }

        newEmployee.setUuid(UUID.randomUUID());
        newEmployee.setFullName(newEmployee.getFirstName() + " " + newEmployee.getLastName());
        employees.add(newEmployee);
        return newEmployee;
    }
}
