package com.example.demo.repositories;

import com.example.demo.entyti.specification.FridgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FridgeRepository extends JpaRepository<FridgeEntity, Long> {

    List<FridgeEntity> findByCompressorType(String compressorType);

    List<FridgeEntity> findByNumberOfDoors(int doors);
}