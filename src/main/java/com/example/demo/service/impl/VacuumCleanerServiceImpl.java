package com.example.demo.service.impl;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.equipment.TypeOfEquipment;
import com.example.demo.entyti.specification.Size;
import com.example.demo.entyti.specification.VacuumCleanerEntity;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SizeRepository;
import com.example.demo.repositories.TypeOfEquipmentRepository;
import com.example.demo.repositories.VacuumCleanerRepository;
import com.example.demo.service.BasicService;
import com.example.demo.service.VacuumCleanerService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class VacuumCleanerServiceImpl implements VacuumCleanerService, BasicService {
    private final VacuumCleanerRepository vacuumCleanerRepository;
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final TypeOfEquipmentRepository typeOfEquipmentRepository;

    public VacuumCleanerServiceImpl(VacuumCleanerRepository vacuumCleanerRepository, ProductRepository productRepository, SizeRepository sizeRepository, TypeOfEquipmentRepository typeOfEquipmentRepository) {
        this.vacuumCleanerRepository = vacuumCleanerRepository;
        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
        this.typeOfEquipmentRepository = typeOfEquipmentRepository;
    }

    @PostConstruct
    public void init() {
        Random rnd = new Random();
        log.info("Initializing VacuumCleanerService");
        List<String> listManufacturer = new ArrayList<>(List.of("Lg", "Beko", "Sharp", "Bosch", "Siemens"));
        List<String> listCountryOfOrigin = new ArrayList<>(List.of("South Korea", "Germany", "Cina", "Japan"));
        List<String> listColor = new ArrayList<>(List.of("Black", "Red", "Green", "White"));
        for (int i = 0; i < 3; i++) {
            Size size = new Size(null, rnd.nextInt(100,200), rnd.nextInt(100,200), rnd.nextInt(100,200));
            TypeOfEquipment typeOfEquipment = new TypeOfEquipment(null, "Vacuum Cleaner");
            ProductEntity product = new ProductEntity(null, "Vacuum Cleaner", listManufacturer.get(rnd.nextInt(listManufacturer.size())), listCountryOfOrigin.get(rnd.nextInt(listCountryOfOrigin.size())),
                    rnd.nextBoolean(),rnd.nextBoolean(), rnd.nextLong(1000,10_000), listColor.get(rnd.nextInt(listColor.size())), rnd.nextDouble(1000,10_000), rnd.nextBoolean(), size, typeOfEquipment);
            VacuumCleanerEntity vacuumCleanerEntity = new VacuumCleanerEntity(null, product, rnd.nextInt(200, 1000), rnd.nextInt(1, 5));
            sizeRepository.save(size);
            typeOfEquipmentRepository.save(typeOfEquipment);
            productRepository.save(product);
            vacuumCleanerRepository.save(vacuumCleanerEntity);
        }
    }

    @Override
    public VacuumCleanerEntity createVacuumCleaner(VacuumCleanerEntity entity) {
        VacuumCleanerEntity vacuumCleanerEntity = new VacuumCleanerEntity();
        ProductEntity productEntity = new ProductEntity();
        Size size = new Size();
        TypeOfEquipment typeOfEquipment = new TypeOfEquipment();
        size.setWidth(entity.getProduct().getSize().getWidth());
        size.setLength(entity.getProduct().getSize().getLength());
        size.setHeight(entity.getProduct().getSize().getHeight());
        typeOfEquipment.setName(entity.getProduct().getTypeOfEquipment().getName());
        productEntity.setName(entity.getProduct().getName());
        productEntity.setManufacturer(entity.getProduct().getManufacturer());
        productEntity.setCountryOfOrigin(entity.getProduct().getCountryOfOrigin());
        productEntity.setOrderOnline(entity.getProduct().getOrderOnline());
        productEntity.setInstallmentPlan(entity.getProduct().getInstallmentPlan());
        productEntity.setSerialNumber(entity.getProduct().getSerialNumber());
        productEntity.setColor(entity.getProduct().getColor());
        productEntity.setSize(size);
        productEntity.setTypeOfEquipment(typeOfEquipment);
        vacuumCleanerEntity.setId(null);
        productEntity.setId(null);
        size.setId(null);
        typeOfEquipment.setId(null);
        vacuumCleanerEntity.setProduct(productEntity);
        vacuumCleanerEntity.setDustContainerVolume(entity.getDustContainerVolume());
        vacuumCleanerEntity.setNumberOfModes(entity.getNumberOfModes());
        log.info("Created Vacuum Cleaner");
        sizeRepository.save(size);
        typeOfEquipmentRepository.save(typeOfEquipment);
        productRepository.save(productEntity);
        return vacuumCleanerRepository.save(vacuumCleanerEntity);
    }

    @Override
    public List<VacuumCleanerEntity> findByDustContainerVolume(int volume) {
        List<VacuumCleanerEntity> findList = vacuumCleanerRepository.findByDustContainerVolume(volume);
        if (findList.isEmpty()) {
            log.warn("No vacuum cleaner found with specified dust container volume");
            throw new ProductNotFoundException("No vacuum cleaner found with specified dust container volume");
        }
        return findList;
    }

    @Override
    public List<VacuumCleanerEntity> findByNumberOfModes(int numberOfModes) {
        List<VacuumCleanerEntity> findList = vacuumCleanerRepository.findByNumberOfModes(numberOfModes);
        if (findList.isEmpty()) {
            log.warn("No vacuum cleaner found with specified number of modes");
            throw new ProductNotFoundException("No vacuum cleaner found with specified number of modes");
        }
        return findList;
    }
    @Override
    public List<ProductEntity> findByName(String name,String type, SortOrder order) {
        List<ProductEntity> productEntity = productRepository.findByNameIgnoreCaseAndTypeOfEquipment_NameIgnoreCase(name, type);
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
    public List<ProductEntity> findByColor(String color, String name, String type) {
        List<ProductEntity> findList = productRepository.findByColorIgnoreCaseAndNameIgnoreCaseAndTypeOfEquipment_NameIgnoreCase(color, name, type);
        if (findList.isEmpty()) {
            log.warn("The method findByColor didn't work");
            throw new ProductNotFoundException("Product with color " + color + " not found");
        }
        log.info("The findByColor method worked");
        return findList;
    }


    @Override
    public List<ProductEntity> findByPriceRange(double minPrice, double maxPrice, SortOrder order, String name) {
        List<ProductEntity> findList = productRepository.findByPriceBetweenAndTypeOfEquipment_NameIgnoreCase(minPrice, maxPrice, name);
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
    public List<ProductEntity> findBySize(int height, int length, int width, String name) {
        List<ProductEntity> findList = productRepository.findBySize_HeightBeforeAndSize_LengthBeforeAndSize_WidthBeforeAndTypeOfEquipment_Name(height, length, width, name);
        if (findList.isEmpty()) {
            log.warn("The method findBySize didn't work");
            throw new ProductNotFoundException("Product with size " + height + "x" + length + "x" + width + " not found");
        }
        log.info("The findBySize method worked");
        return findList;
    }

    @Override
    public List<ProductEntity> findByCountry(String country, String type, SortOrder order) {
        List<ProductEntity> findList = productRepository.findByCountryOfOriginIgnoreCaseAndTypeOfEquipment_NameIgnoreCase(country, type);
        if (findList.isEmpty()) {
            log.warn("The method findByCounty didn't work");
            throw new ProductNotFoundException("Product from " + country + " not found");
        }
        if (order.equals(SortOrder.ASCENDING)) {
            findList.sort(Comparator.comparing(ProductEntity::getCountryOfOrigin));
        } else if (order.equals(SortOrder.DESCENDING)) {
            findList.sort(Comparator.comparing(ProductEntity::getCountryOfOrigin).reversed());
        }
        log.info("The findByCounty method worked");
        return findList;
    }

    @Override
    public List<ProductEntity> findByManufacturer(String manufacturer, String type, SortOrder order) {
        List<ProductEntity> findList = productRepository.findByManufacturerIgnoreCaseAndTypeOfEquipment_NameIgnoreCase(manufacturer, type);
        if (findList.isEmpty()) {
            log.warn("The method findByManufacturer didn't work");
            throw new ProductNotFoundException("Product from " + manufacturer + " not found");
        }
        if (order.equals(SortOrder.ASCENDING)) {
            findList.sort(Comparator.comparing(ProductEntity::getManufacturer));
        } else if (order.equals(SortOrder.DESCENDING)) {
            findList.sort(Comparator.comparing(ProductEntity::getManufacturer).reversed());
        }
        log.info("The findByManufacturer method worked");
        return findList;
    }

    @Override
    public List<ProductEntity> findByOrderOnline(Boolean orderOnline, String type) {
        List<ProductEntity> findList = productRepository.findByOrderOnlineAndTypeOfEquipment_NameIgnoreCase(orderOnline, type);
        if (findList.isEmpty()) {
            log.warn("The method findByOrderOnline didn't work");
            throw new ProductNotFoundException("Product with order online status " + orderOnline + " not found");
        }
        log.info("The findByOrderOnline method worked");
        return findList;
    }

    @Override
    public List<ProductEntity> findByInstallmentPlan(Boolean installmentPlan, String type) {
        List<ProductEntity> findList = productRepository.findByInstallmentPlanAndTypeOfEquipment_NameIgnoreCase(installmentPlan, type);
        if (findList.isEmpty()) {
            log.warn("The method findByInstallmentPlan didn't work");
            throw new ProductNotFoundException("Product with installment plan " + installmentPlan + " not found");
        }
        log.info("The findByInstallmentPlan method worked");
        return findList;
    }

    @Override
    public List<ProductEntity> productInStock(String type, boolean availability) {
        return productRepository.findByTypeOfEquipment_NameIgnoreCaseAndProductAvailability(type, availability);
    }
}
