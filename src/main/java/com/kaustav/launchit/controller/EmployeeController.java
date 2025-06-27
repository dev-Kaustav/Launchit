package com.kaustav.launchit.controller;

import com.kaustav.launchit.db.Employee;
import com.kaustav.launchit.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for employee management endpoints.
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    /** List all employees. */
    @GetMapping
    public List<Employee> all() {
        log.debug("GET /api/employees");
        return service.findAll();
    }

    /** Get a specific employee. */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable int id) {
        log.debug("GET /api/employees/{}", id);
        Employee emp = service.find(id);
        return emp != null ? ResponseEntity.ok(emp) : ResponseEntity.notFound().build();
    }

    /** Create a new employee record. */
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        log.info("POST /api/employees");
        return service.create(employee);
    }

    /** Update an employee record. */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable int id, @RequestBody Employee employee) {
        log.info("PUT /api/employees/{}", id);
        if (service.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.update(id, employee));
    }

    /** Delete an employee. */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        log.info("DELETE /api/employees/{}", id);
        service.delete(id);
    }
}
