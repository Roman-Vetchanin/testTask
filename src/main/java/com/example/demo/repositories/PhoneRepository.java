package com.example.demo.repositories;

import com.example.demo.entyti.specification.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {

    List<PhoneEntity> findByMemoryCapacity(int memoryCapacity);

    List<PhoneEntity> findByNumberOfCameras(int numberOfCameras);
}