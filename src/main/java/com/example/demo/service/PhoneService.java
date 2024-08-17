package com.example.demo.service;

import com.example.demo.entyti.specification.PhoneEntity;

import java.util.List;

public interface PhoneService {
    PhoneEntity createPhone(PhoneEntity entity);

    List<PhoneEntity> findByNumberOfCameras(int numberOfCameras);

    List<PhoneEntity> findByMemoryCapacity(int memoryCapacity);
}
