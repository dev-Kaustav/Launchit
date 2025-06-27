package com.kaustav.launchit.service;
import com.kaustav.launchit.db.Employee;
import com.kaustav.launchit.db.EmployeeRepository;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Business logic for {@link Employee} entities.
 */
@Service
public class EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieve all employees.
     */
    public List<Employee> findAll() {
        log.debug("Fetching all employees");
        return repository.findAll();
    }

    /**
     * Find an employee by id.
     */
    public Employee find(int id) {
        log.debug("Fetching employee {}", id);
        return repository.findById(id).orElse(null);
    }

    /**
     * Create a new employee record.
     */
    public Employee create(Employee employee) {
        log.info("Creating employee {}", employee.getFullName());
        return repository.save(employee);
    }

    /** Update an existing employee. */
    public Employee update(int id, Employee employee) {
        employee.setId(id);
        log.info("Updating employee {}", id);
        return repository.save(employee);
    }

    /** Delete an employee by id. */
    public void delete(int id) {
        log.info("Deleting employee {}", id);
        repository.deleteById(id);
    }
}
