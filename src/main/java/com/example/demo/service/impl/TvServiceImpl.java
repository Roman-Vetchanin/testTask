package com.example.demo.service.impl;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.specification.TvEntity;
import com.example.demo.repositories.TvRepository;
import com.example.demo.service.TvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class TvServiceImpl implements TvService {
    private final TvRepository tvRepository;

    public TvServiceImpl(TvRepository tvRepository) {
        this.tvRepository = tvRepository;
    }

    @Override
    public TvEntity createTv(TvEntity entity) {
        TvEntity tvEntity = new TvEntity();
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
        tvEntity.setId(null);
        productEntity.setId(null);
        tvEntity.setProduct(productEntity);
        tvEntity.setCategory(entity.getCategory());
        tvEntity.setTechnology(entity.getTechnology());
        log.info("Tv created");
        return tvRepository.save(tvEntity);
    }

    @Override
    public List<TvEntity> findByCategory(String category) {
        List<TvEntity> findList = tvRepository.findByCategory(category);
        if (findList.isEmpty()) {
            log.warn("The method findByCategory didn't work");
            throw new RuntimeException("No TVs found with category: " + category);
        }
        log.info("The findByCategory method worked");
        return findList;
    }

    @Override
    public List<TvEntity> findByTechnology(String technology) {
        List<TvEntity> findList = tvRepository.findByTechnology(technology);
        if (findList.isEmpty()) {
            log.warn("The method findByTechnology didn't work");
            throw new RuntimeException("No TVs found with technology: " + technology);
        }
        log.info("The findByTechnology method worked");
        return findList;
    }
}
