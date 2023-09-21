package org.firesunset.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.firesunset.model.Employee;
import org.firesunset.model.Equipment;
import org.firesunset.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("employee")
@Tag(name = "Проведение операций над сотрудниками")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @Operation(summary = "Добавить нового сотрудника")
    public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee employeeRb) {
        Employee employee = employeeService.createNewEmployee(employeeRb);
        return ResponseEntity.ok(employee);
    }

    @PutMapping
    @Operation(summary = "Изменить данные сотрудника(поиск по id)")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employeeRb) {
        Employee employee = employeeService.updateEmployee(employeeRb);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удалить сотрудника по id")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("all")
    @Operation(summary = "Удалить всех сотрудников")
    public ResponseEntity<Collection<Employee>> deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    @Operation(summary = "Получить сотрудника по id")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("all")
    @Operation(summary = "Получить всех сотрудников")
    public ResponseEntity<Collection<Employee>> getAllEmployees() {
        Collection<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping
    @Operation(summary = "Получить сотрудника по имени и фамилии")
    public ResponseEntity<Employee> getEmployeeByFirstNameAndLastName(@RequestParam String firstName,
                                                                      @RequestParam String lastName) {
        Employee employee = employeeService.getEmployeeByFirstNameAndLastName(firstName, lastName);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("equipments/{employeeId}")
    @Operation(summary = "Получить спикок эксплутируемого сотрудником оборудования")
    public ResponseEntity<Collection<Equipment>> getAllEquipmentsByEmployeeId(@PathVariable("employeeId") Long id) {
        Collection<Equipment> equipments = employeeService.getEmployeeById(id).getEquipments();
        return ResponseEntity.ok(equipments);
    }
}
