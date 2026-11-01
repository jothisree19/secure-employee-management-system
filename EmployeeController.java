package com.example.secureemployeeapp.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.secureemployeeapp.entity.Employee;
import com.example.secureemployeeapp.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/public")
    public String publicAccess() {
        return "Welcome! This page is accessible to everyone.";
    }

    @GetMapping("/employee")
    public String employeeAccess() {
        return "Welcome! You have successfully accessed the employee endpoint.";
    }

    @PostMapping("/api/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/api/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/api/employees/page")
    public Page<Employee> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeService.getEmployees(pageable);
    }
    @GetMapping("/api/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,("employee not found")));
    }

    @PutMapping("/api/employees/{id}")
    public Employee updatedEmployee(@PathVariable Long id,
                                    @RequestBody Employee updatedEmployee) {
        Employee existingEmployee = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new RuntimeException("employee not found"));

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setSalary(updatedEmployee.getSalary());

        return employeeService.saveEmployee(existingEmployee);
    }

    @DeleteMapping("/api/employees/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        employeeService.getEmployeeById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employeeService.deleteEmployee(id);

        return "Employee deleted successfully!";
    }
}





