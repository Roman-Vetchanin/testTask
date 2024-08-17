package com.example.demo.service;

import com.example.demo.entyti.specification.FridgeEntity;

import java.util.List;

public interface FridgeService {
    FridgeEntity createFridge(FridgeEntity entity);

    List<FridgeEntity> findByCompressor(String compressor);

    List<FridgeEntity> findByNumberOfDoors(int doors);
}
