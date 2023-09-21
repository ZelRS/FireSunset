package org.firesunset.service;

import org.firesunset.model.Equipment;

import java.util.Collection;

public interface EquipmentService {
    Equipment createNewEquipment(Equipment equipment);

    Equipment updateEquipment(Equipment equipment);

    void deleteEquipmentById(Long id);

    void deleteAllEquipments();

    Equipment getEquipmentById(Long id);

    Collection<Equipment> getAllEquipments();

    Collection<Equipment> getAllEquipmentByName(String name);

    void assignEquipmentToEmployee(Long employeeId, Long equipmentId);

    void returnEquipmentToEmployee(Long equipmentId);

    void returnAllEquipment();
}
