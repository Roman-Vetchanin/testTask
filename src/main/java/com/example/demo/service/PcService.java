package com.example.demo.service;

import com.example.demo.entyti.specification.PcEntity;

import java.util.List;

public interface PcService {
    PcEntity createPc(PcEntity entity);

    List<PcEntity> findByCategory(String category);

    List<PcEntity> findByProcessor(String processor);
}
