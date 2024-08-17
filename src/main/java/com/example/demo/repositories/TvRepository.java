package com.example.demo.repositories;

import com.example.demo.entyti.specification.TvEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TvRepository extends JpaRepository<TvEntity, Long> {

    List<TvEntity> findByCategory(String category);

    List<TvEntity> findByTechnology(String technology);
}