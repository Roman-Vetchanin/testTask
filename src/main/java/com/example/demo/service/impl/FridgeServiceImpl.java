package com.example.demo.service.impl;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.specification.FridgeEntity;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repositories.FridgeRepository;
import com.example.demo.service.FridgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class FridgeServiceImpl implements FridgeService {

    private final FridgeRepository fridgeRepository;

    public FridgeServiceImpl(FridgeRepository fridgeRepository) {
        this.fridgeRepository = fridgeRepository;
    }

    @Override
    public FridgeEntity createFridge(FridgeEntity entity) {
        FridgeEntity fridge = new FridgeEntity();
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
        fridge.setId(null);
        productEntity.setId(null);
        fridge.setProduct(productEntity);
        fridge.setCompressorType(entity.getCompressorType());
        fridge.setNumberOfDoors(entity.getNumberOfDoors());
        log.info("Created Fridge");
        return fridgeRepository.save(fridge);
    }

    @Override
    public List<FridgeEntity> findByCompressor(String compressor) {
        List<FridgeEntity> findList = fridgeRepository.findByCompressorType(compressor);
        if(findList.isEmpty()){
            log.warn("Compressor not found: " + compressor);
            throw new ProductNotFoundException("Compressor type " + compressor + " not found");
        }
        log.info("Compressor is found");
        return findList;
    }

    @Override
    public List<FridgeEntity> findByNumberOfDoors(int doors) {
        List<FridgeEntity> findList = fridgeRepository.findByNumberOfDoors(doors);
        if(findList.isEmpty()){
            log.warn("No refrigerator found with suitable quantity of doors");
            throw new ProductNotFoundException("Refrigerator with suitable quantity "+ doors + " doors not found");
        }
        log.info("Refrigerator is found");
        return findList;
    }
}
