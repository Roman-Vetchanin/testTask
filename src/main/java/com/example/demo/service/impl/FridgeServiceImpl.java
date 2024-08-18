package com.example.demo.service.impl;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.equipment.TypeOfEquipment;
import com.example.demo.entyti.specification.FridgeEntity;
import com.example.demo.entyti.specification.Size;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repositories.FridgeRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SizeRepository;
import com.example.demo.repositories.TypeOfEquipmentRepository;
import com.example.demo.service.BasicService;
import com.example.demo.service.FridgeService;
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
public class FridgeServiceImpl implements FridgeService, BasicService {

    private final FridgeRepository fridgeRepository;
    private final SizeRepository sizeRepository;
    private final ProductRepository productRepository;
    private final TypeOfEquipmentRepository typeOfEquipmentRepository;

    public FridgeServiceImpl(FridgeRepository fridgeRepository, SizeRepository sizeRepository, ProductRepository productRepository, TypeOfEquipmentRepository typeOfEquipmentRepository) {
        this.fridgeRepository = fridgeRepository;
        this.sizeRepository = sizeRepository;
        this.productRepository = productRepository;
        this.typeOfEquipmentRepository = typeOfEquipmentRepository;
    }

    @PostConstruct
    private void init() {
        log.info("Initializing FridgeService");
        Random random = new Random();
        List<String> listManufacturer = new ArrayList<>(List.of("Lg", "Beko", "Sharp", "Bosch", "Siemens"));
        List<String> listCountryOfOrigin = new ArrayList<>(List.of("South Korea", "Germany", "Cina", "Japan"));
        List<String> listColor = new ArrayList<>(List.of("Black", "Red", "Green", "White"));
        List<String> listCompressor = new ArrayList<>(List.of("Linear type refrigerator", "Inverter compressor type"));
        for (int i = 0; i < 3; i++) {
            Size size = new Size(null, random.nextInt(1, 200), random.nextInt(1, 200), random.nextInt(1, 200));
            TypeOfEquipment typeOfEquipment = new TypeOfEquipment(null, "Fridge");
            ProductEntity productEntity = new ProductEntity(null, "Fridge", listManufacturer.get(random.nextInt(listManufacturer.size())),
                    listCountryOfOrigin.get(random.nextInt(listCountryOfOrigin.size())), random.nextBoolean(), random.nextBoolean(),
                    random.nextLong(1000, 10_00000), listColor.get(random.nextInt(listColor.size())), random.nextDouble(100, 10_000), random.nextBoolean(), size, typeOfEquipment);
            FridgeEntity fridge = new FridgeEntity(null, productEntity, random.nextInt(1, 5), listCompressor.get(random.nextInt(listCompressor.size())));
            sizeRepository.save(size);
            typeOfEquipmentRepository.save(typeOfEquipment);
            productRepository.save(productEntity);
            fridgeRepository.save(fridge);
        }

    }

    @Override
    public FridgeEntity createFridge(FridgeEntity entity) {
        FridgeEntity fridge = new FridgeEntity();
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
        fridge.setId(null);
        productEntity.setId(null);
        size.setId(null);
        typeOfEquipment.setId(null);
        fridge.setProduct(productEntity);
        fridge.setCompressorType(entity.getCompressorType());
        fridge.setNumberOfDoors(entity.getNumberOfDoors());
        log.info("Created Fridge");
        sizeRepository.save(size);
        typeOfEquipmentRepository.save(typeOfEquipment);
        productRepository.save(productEntity);
        return fridgeRepository.save(fridge);
    }

    @Override
    public List<FridgeEntity> findByCompressor(String compressor) {
        List<FridgeEntity> findList = fridgeRepository.findByCompressorType(compressor);
        if (findList.isEmpty()) {
            log.warn("Compressor not found: " + compressor);
            throw new ProductNotFoundException("Compressor type " + compressor + " not found");
        }
        log.info("Compressor is found");
        return findList;
    }

    @Override
    public List<FridgeEntity> findByNumberOfDoors(int doors) {
        List<FridgeEntity> findList = fridgeRepository.findByNumberOfDoors(doors);
        if (findList.isEmpty()) {
            log.warn("No refrigerator found with suitable quantity of doors");
            throw new ProductNotFoundException("Refrigerator with suitable quantity " + doors + " doors not found");
        }
        log.info("Refrigerator is found");
        return findList;
    }

    @Override
    public List<ProductEntity> findByName(String name,String type, SortOrder order) {
        List<ProductEntity> productEntity = productRepository.findByNameIgnoreCaseAndTypeOfEquipment_NameIgnoreCase(name,type);
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
    public List<ProductEntity> findByPriceRange(double minPrice, double maxPrice, SortOrder order, String type) {
        List<ProductEntity> findList = productRepository.findByPriceBetweenAndTypeOfEquipment_NameIgnoreCase(minPrice, maxPrice, type);
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
    public List<ProductEntity> findBySize(int height, int length, int width, String type) {
        List<ProductEntity> findList = productRepository.findBySize_HeightBeforeAndSize_LengthBeforeAndSize_WidthBeforeAndTypeOfEquipment_Name(height, length, width, type);
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
