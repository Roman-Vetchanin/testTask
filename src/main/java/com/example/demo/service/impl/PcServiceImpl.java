package com.example.demo.service.impl;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.specification.PcEntity;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repositories.PcRepository;
import com.example.demo.service.PcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class PcServiceImpl implements PcService {
    private final PcRepository pcRepository;

    public PcServiceImpl(PcRepository pcRepository) {
        this.pcRepository = pcRepository;
    }

    @Override
    public PcEntity createPc(PcEntity entity) {
        PcEntity pcEntity = new PcEntity();
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
        pcEntity.setId(null);
        productEntity.setId(null);
        pcEntity.setProduct(productEntity);
        pcEntity.setCategory(entity.getCategory());
        pcEntity.setProcessorType(entity.getProcessorType());
        log.info("Crated Pc");
        return pcRepository.save(pcEntity);
    }

    @Override
    public List<PcEntity> findByCategory(String category) {
        List<PcEntity> findList = pcRepository.findByCategory(category);
        if (findList.isEmpty()){
            log.warn("Cannot find Pc");
            throw new ProductNotFoundException("There are no products in this category " + category);
        }
        log.info("Pc found");
        return findList;
    }

    @Override
    public List<PcEntity> findByProcessor(String processor) {
        List<PcEntity> findList = pcRepository.findByProcessorType(processor);
        if (findList.isEmpty()){
            throw new ProductNotFoundException("A computer with this "+processor+" was not found");
        }
        return findList;
    }
}
