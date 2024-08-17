package com.example.demo.repositories;

import com.example.demo.entyti.specification.VacuumCleanerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacuumCleanerRepository extends JpaRepository<VacuumCleanerEntity, Long> {

    List<VacuumCleanerEntity> findByDustContainerVolume(int volume);

    List<VacuumCleanerEntity> findByNumberOfModes(int numberOfModes);
}