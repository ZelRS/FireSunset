package org.firesunset.service.impl;

import org.firesunset.exception.EmployeeNotFoundException;
import org.firesunset.exception.EquipmentNotFoundException;
import org.firesunset.model.Employee;
import org.firesunset.model.Equipment;
import org.firesunset.repository.EmployeeRepository;
import org.firesunset.repository.EquipmentRepository;
import org.firesunset.service.EquipmentService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final EmployeeRepository employeeRepository;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository,
                                EmployeeRepository employeeRepository) {
        this.equipmentRepository = equipmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Equipment createNewEquipment(Equipment equipment) {

        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment updateEquipment(Equipment equipment1) {
        Optional<Equipment> Equipment = equipmentRepository.findById(equipment1.getId());
        throwIfEquipmentIsNotFound(Equipment);
        return equipmentRepository.save(equipment1);
    }

    @Override
    public void deleteEquipmentById(Long id) {
        Optional<Equipment> Equipment = equipmentRepository.findById(id);
        throwIfEquipmentIsNotFound(Equipment);
        equipmentRepository.deleteById(id);
    }

    @Override
    public void deleteAllEquipments() {
        equipmentRepository.deleteAll();
    }

    @Override
    public Equipment getEquipmentById(Long id) {
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        throwIfEquipmentIsNotFound(equipment);
        return equipment.get();
    }

    @Override
    public Collection<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    @Override
    public Collection<Equipment> getAllEquipmentByName(String name) {
        return equipmentRepository.getAllByName(name);
    }

    @Override
    public void assignEquipmentToEmployee(Long employeeId, Long equipmentId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new EmployeeNotFoundException("Сотрудник не найден"));

        Equipment equipment = equipmentRepository.findById((equipmentId))
                .orElseThrow(()-> new EquipmentNotFoundException("Наименование не найдено"));

        equipment.setEmployee(employee);
        equipmentRepository.save(equipment);
    }

    @Override
    public void returnEquipmentToEmployee(Long equipmentId) {
        Equipment equipment = equipmentRepository.findById((equipmentId))
                .orElseThrow(()-> new EquipmentNotFoundException("Наименование не найдено"));

        equipment.setEmployee(null);
        equipmentRepository.save(equipment);

    }

    @Override
    public void returnAllEquipment() {
        Collection<Equipment> equipments = equipmentRepository.findAll().stream()
                .filter(e -> e.getEmployee() != null)
                .collect(Collectors.toList());
        for (Equipment e : equipments) {
            e.setEmployee(null);
            equipmentRepository.save(e);
        }
    }

    private void throwIfEquipmentIsNotFound(Optional<Equipment> equipment) {
        if (equipment.isEmpty()) {
            throw new EquipmentNotFoundException("Наименование не найдено");
        }
    }
}
