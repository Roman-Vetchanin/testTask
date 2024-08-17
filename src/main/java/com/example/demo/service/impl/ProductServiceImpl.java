package com.example.demo.service.impl;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.equipment.TypeOfEquipment;
import com.example.demo.entyti.specification.Size;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SizeRepository;
import com.example.demo.repositories.TypeOfEquipmentRepository;
import com.example.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final TypeOfEquipmentRepository typeOfEquipmentRepository;

    public ProductServiceImpl(ProductRepository productRepository, SizeRepository sizeRepository, TypeOfEquipmentRepository typeOfEquipmentRepository) {
        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
        this.typeOfEquipmentRepository = typeOfEquipmentRepository;
    }


    @Override
    public ProductEntity createProduct(ProductEntity entity) {
        ProductEntity productEntity = new ProductEntity();
        Size size = new Size();
        TypeOfEquipment typeOfEquipment = new TypeOfEquipment();
        size.setWidth(entity.getSize().getWidth());
        size.setLength(entity.getSize().getLength());
        size.setHeight(entity.getSize().getHeight());
        typeOfEquipment.setName(entity.getTypeOfEquipment().getName());
        productEntity.setName(entity.getName());
        productEntity.setManufacturer(entity.getManufacturer());
        productEntity.setCountryOfOrigin(entity.getCountryOfOrigin());
        productEntity.setOrderOnline(entity.getOrderOnline());
        productEntity.setInstallmentPlan(entity.getInstallmentPlan());
        productEntity.setSerialNumber(entity.getSerialNumber());
        productEntity.setColor(entity.getColor());
        productEntity.setSize(size);
        productEntity.setTypeOfEquipment(typeOfEquipment);
        log.info("Product created");
        sizeRepository.save(size);
        typeOfEquipmentRepository.save(typeOfEquipment);
        return productRepository.save(productEntity);
    }

    @Override
    public List<ProductEntity> findByName(String name, SortOrder order) {
        List<ProductEntity> productEntity = productRepository.findByNameIgnoreCase(name);
        if (productEntity.isEmpty()) {
            log.warn("The method findByName didn't work");
            throw new ProductNotFoundException("Product name " + name + " not found");
        }
        if (order.equals(SortOrder.ASCENDING)) {
            log.info("Sort order {}", order);
            productEntity.sort(Comparator.comparing(ProductEntity::getManufacturer));
        } else if (order.equals(SortOrder.DESCENDING)) {
            log.info("Sort order {}", order);
            productEntity.sort(Comparator.comparing(ProductEntity::getManufacturer).reversed());
        }
        log.info("The findByName method worked");
        return productEntity;
    }

    @Override
    public List<ProductEntity> findBySize(int height, int length, int width) {
        List<ProductEntity> findList = productRepository.findBySize_HeightBeforeAndSize_LengthBeforeAndSize_WidthBefore(height, length, width);
        if (findList.isEmpty()) {
            log.warn("The method findBySize didn't work");
            throw new ProductNotFoundException("Product with size " + height + "x" + length + "x" + width + " not found");
        }
        log.info("The findBySize method worked");
        return findList;
    }

    @Override
    public List<ProductEntity> findByPriceRange(double minPrice, double maxPrice, SortOrder order) {
        List<ProductEntity> findList = productRepository.findByPriceBetween(minPrice, maxPrice);
        if (findList.isEmpty()) {
            log.warn("The method findByPriceRange didn't work");
            throw new ProductNotFoundException("Product with price between " + minPrice + " and " + maxPrice + " not found");
        }
        if (order.equals(SortOrder.ASCENDING)) {
           findList.sort(Comparator.comparing(ProductEntity::getPrice));
        } else if (order.equals(SortOrder.DESCENDING)) {
            findList.sort(Comparator.comparing(ProductEntity::getPrice).reversed());
        }
        log.info("The findByPriceRange method worked");
        return findList;
    }

    @Override
    public ProductEntity findBySerialNumber(long serialNumber) {
        ProductEntity findList = productRepository.findBySerialNumber(serialNumber);
        if (findList == null) {
            log.warn("The method findBySerialNumber didn't work");
            throw new ProductNotFoundException("Product with serial number " + serialNumber + " not found");
        }
        log.info("The findBySerialNumber method worked");
        return findList;
    }

    @Override
    public List<ProductEntity> findByCounty(String country) {
        List<ProductEntity> findList = productRepository.findByCountryOfOriginIgnoreCase(country.toLowerCase());
        if (findList.isEmpty()) {
            log.warn("The method findByCounty didn't work");
            throw new ProductNotFoundException("Product from " + country + " not found");
        }
        log.info("The findByCounty method worked");
        return findList;
    }

    @Override
    public List<ProductEntity> findByManufacturer(String manufacturer) {
        List<ProductEntity> findList = productRepository.findByManufacturerIgnoreCase(manufacturer.toLowerCase());
        if (findList.isEmpty()) {
            log.warn("The method findByManufacturer didn't work");
            throw new ProductNotFoundException("Product from " + manufacturer + " not found");
        }
        log.info("The findByManufacturer method worked");
        return findList;
    }

    @Override
    public List<ProductEntity> findByOrderOnline(Boolean orderOnline) {
        List<ProductEntity> findList = productRepository.findByOrderOnline(orderOnline);
        if (findList.isEmpty()) {
            log.warn("The method findByOrderOnline didn't work");
            throw new ProductNotFoundException("Product with order online status " + orderOnline + " not found");
        }
        log.info("The findByOrderOnline method worked");
        return findList;
    }

    @Override
    public List<ProductEntity> findByInstallmentPlan(Boolean installmentPlan) {
        List<ProductEntity> findList = productRepository.findByInstallmentPlan(installmentPlan);
        if (findList.isEmpty()) {
            log.warn("The method findByInstallmentPlan didn't work");
            throw new ProductNotFoundException("Product with installment plan " + installmentPlan + " not found");
        }
        log.info("The findByInstallmentPlan method worked");
        return findList;
    }

    @Override
    public List<ProductEntity> findByType(String type) {
        List<ProductEntity> findList = productRepository.findByTypeOfEquipment_NameIgnoreCase(type.toLowerCase());
        if (findList.isEmpty()) {
            log.warn("The method findByType didn't work");
            throw new ProductNotFoundException("Product of type " + type + " not found");
        }
        log.info("The findByType method worked");
        return findList;
    }

    @Override
    public List<ProductEntity> findByColor(String color) {
        List<ProductEntity> findList = productRepository.findByColorIgnoreCase(color.toLowerCase());
        if (findList.isEmpty()) {
            log.warn("The method findByColor didn't work");
            throw new ProductNotFoundException("Product with color " + color + " not found");
        }

        log.info("The findByColor method worked");
        return findList;
    }
}
