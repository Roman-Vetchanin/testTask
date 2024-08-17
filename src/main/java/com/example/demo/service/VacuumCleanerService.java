package com.example.demo.service;

import com.example.demo.entyti.specification.VacuumCleanerEntity;

import java.util.List;

public interface VacuumCleanerService {
    VacuumCleanerEntity createVacuumCleaner(VacuumCleanerEntity entity);

    List<VacuumCleanerEntity> findByDustContainerVolume(int volume);

    List<VacuumCleanerEntity> findByNumberOfModes(int numberOfModes);
}
