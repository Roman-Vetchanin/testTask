package com.example.demo.repositories;

import com.example.demo.entyti.specification.PcEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PcRepository extends JpaRepository<PcEntity, Long> {

    List<PcEntity> findByCategory(String category);

    List<PcEntity> findByProcessorType(String processorType);

}