package com.example.demo.repositories;

import com.example.demo.entyti.equipment.TypeOfEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeOfEquipmentRepository extends JpaRepository<TypeOfEquipment, Long> {
}