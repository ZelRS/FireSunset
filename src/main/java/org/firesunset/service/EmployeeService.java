package org.firesunset.service;

import org.firesunset.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee createNewEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployeeById(Long id);

    void deleteAllEmployees();

    Employee getEmployeeById(Long id);

    Collection<Employee> getAllEmployees();

    Employee getEmployeeByFirstNameAndLastName(String firstName, String lastName);
}
