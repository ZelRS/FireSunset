package org.firesunset.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.firesunset.model.Employee;
import org.firesunset.model.Equipment;
import org.firesunset.service.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("equipment")
@Tag(name = "Проведение операций над оборудованием склада")
public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("{employeeId}/assign/{equipmentId}")
    @Operation(summary = "Дать сотруднику оборудование в эксплуатацию")
    public ResponseEntity<String> assignEquipmentToEmployee(@PathVariable("employeeId") Long employeeId,
                                                            @PathVariable("equipmentId") Long equipmentId) {
        equipmentService.assignEquipmentToEmployee(employeeId, equipmentId);
        return ResponseEntity.ok("Оборудование взято в эксплуатацию");
    }

    @PostMapping
    @Operation(summary = "Добавить новое наименование")
    public ResponseEntity<Equipment> createNewEquipment(@RequestBody Equipment equipmentRb) {
        Equipment equipment = equipmentService.createNewEquipment(equipmentRb);
        return ResponseEntity.ok(equipment);
    }

    @PostMapping("return")
    @Operation(summary = "Вернуть оборудование из эксплуатации")
    public ResponseEntity<String> returnEquipmentToEmployee(@RequestParam(required = false) Long equipmentId) {
        if (equipmentId == null) {
            equipmentService.returnAllEquipment();
            return ResponseEntity.ok("Всё борудование возвращено на склад");
        } else {
            equipmentService.returnEquipmentToEmployee(equipmentId);
            return ResponseEntity.ok("Оборудование " + equipmentId + " возвращено на склад");
        }
    }

    @PutMapping
    @Operation(summary = "Изменить данные наименования(поиск по id)")
    public ResponseEntity<Equipment> updateEquipment(@RequestBody Equipment equipmentRb) {
        Equipment equipment = equipmentService.updateEquipment(equipmentRb);
        return ResponseEntity.ok(equipment);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удалить наименование по id")
    public ResponseEntity<Equipment> deleteEquipmentById(@PathVariable Long id) {
        equipmentService.deleteEquipmentById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("all")
    @Operation(summary = "Удалить все наименования")
    public ResponseEntity<Collection<Equipment>> deleteAllEquipments() {
        equipmentService.deleteAllEquipments();
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    @Operation(summary = "Получить сотрудника по id")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable("id") Long id) {
        Equipment equipment = equipmentService.getEquipmentById(id);
        return ResponseEntity.ok(equipment);
    }

    @GetMapping("all")
    @Operation(summary = "Получить все наименования")
    public ResponseEntity<Collection<Equipment>> getAllEquipments() {
        Collection<Equipment> equipment = equipmentService.getAllEquipments();
        return ResponseEntity.ok(equipment);
    }

    @GetMapping
    @Operation(summary = "Получить наименование по названию")
    public ResponseEntity<Collection<Equipment>> getAllEquipmentByName(@RequestParam String name) {
        Collection<Equipment> equipment = equipmentService.getAllEquipmentByName(name);
        return ResponseEntity.ok(equipment);
    }

    @GetMapping("employee/{equipmentId}")
    @Operation(summary = "Получить эскплуатирующего сотрудника по id оборудования")
    public ResponseEntity<Employee> getEmployeeByEquipmentId(@PathVariable("equipmentId") Long id) {
        Employee employee = equipmentService.getEquipmentById(id).getEmployee();
        return ResponseEntity.ok(employee);
    }

}
