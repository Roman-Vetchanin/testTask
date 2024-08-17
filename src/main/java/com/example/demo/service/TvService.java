package com.example.demo.service;

import com.example.demo.entyti.specification.TvEntity;

import java.util.List;

public interface TvService {
    TvEntity createTv(TvEntity entity);

    List<TvEntity> findByCategory(String category);

    List<TvEntity> findByTechnology(String technology);
}
