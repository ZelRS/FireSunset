package org.firesunset.repository;

import org.firesunset.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Collection<Equipment> getAllByName(String name);
}
