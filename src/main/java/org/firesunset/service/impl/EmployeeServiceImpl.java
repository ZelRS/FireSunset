package org.firesunset.service.impl;

import org.firesunset.exception.EmployeeNotFoundException;
import org.firesunset.model.Employee;
import org.firesunset.repository.EmployeeRepository;
import org.firesunset.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee1) {
        Optional<Employee> employee = employeeRepository.findById(employee1.getId());
        throwIfEmployeeIsNotFound(employee);
        return employeeRepository.save(employee1);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        throwIfEmployeeIsNotFound(employee);
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        throwIfEmployeeIsNotFound(employee);
        return employee.get();
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeByFirstNameAndLastName(String firstName, String lastName) {
        Optional<Employee> employee = employeeRepository.getEmployeeByFirstNameAndLastName(firstName, lastName);
        throwIfEmployeeIsNotFound(employee);
        return employee.get();
    }

    private void throwIfEmployeeIsNotFound(Optional<Employee> employee) {
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }
}
