package com.example.demo.service.impl;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.specification.PhoneEntity;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repositories.PhoneRepository;
import com.example.demo.service.PhoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;

    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public PhoneEntity createPhone(PhoneEntity entity) {
        PhoneEntity phoneEntity = new PhoneEntity();
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(entity.getProduct().getName());
        productEntity.setManufacturer(entity.getProduct().getManufacturer());
        productEntity.setCountryOfOrigin(entity.getProduct().getCountryOfOrigin());
        productEntity.setOrderOnline(entity.getProduct().getOrderOnline());
        productEntity.setInstallmentPlan(entity.getProduct().getInstallmentPlan());
        productEntity.setSerialNumber(entity.getProduct().getSerialNumber());
        productEntity.setColor(entity.getProduct().getColor());
        productEntity.setSize(entity.getProduct().getSize());
        productEntity.setTypeOfEquipment(entity.getProduct().getTypeOfEquipment());
        phoneEntity.setId(null);
        productEntity.setId(null);
        phoneEntity.setProduct(productEntity);
        phoneEntity.setMemoryCapacity(entity.getMemoryCapacity());
        phoneEntity.setNumberOfCameras(entity.getNumberOfCameras());
        log.info("Created Phone");
        return phoneRepository.save(phoneEntity);
    }

    @Override
    public List<PhoneEntity> findByNumberOfCameras(int numberOfCameras) {
        List<PhoneEntity> findList = phoneRepository.findByNumberOfCameras(numberOfCameras);
        if (findList.isEmpty()) {
            log.warn("The method findByNumberOfCameras didn't work");
            throw new ProductNotFoundException("There is no phone with so many cameras" + numberOfCameras);
        }
        log.info("The findByNumberOfCameras method worked");
        return findList;
    }

    @Override
    public List<PhoneEntity> findByMemoryCapacity(int memoryCapacity) {
        List<PhoneEntity> findList = phoneRepository.findByMemoryCapacity(memoryCapacity);
        if (findList.isEmpty()) {
            log.warn("The method findByMemoryCapacity didn't work");
            throw new ProductNotFoundException("There is no phone with such memory capacity" + memoryCapacity);
        }
        log.info("The findByMemoryCapacity method worked");
        return findList;
    }
}
