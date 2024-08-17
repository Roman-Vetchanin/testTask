package com.example.demo.service.impl;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.equipment.TypeOfEquipment;
import com.example.demo.entyti.specification.PcEntity;
import com.example.demo.entyti.specification.PhoneEntity;
import com.example.demo.entyti.specification.Size;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repositories.PhoneRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SizeRepository;
import com.example.demo.repositories.TypeOfEquipmentRepository;
import com.example.demo.service.PhoneService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;
    private final SizeRepository sizeRepository;
    private final ProductRepository productRepository;
    private final TypeOfEquipmentRepository typeOfEquipmentRepository;

    public PhoneServiceImpl(PhoneRepository phoneRepository, SizeRepository sizeRepository, ProductRepository productRepository, TypeOfEquipmentRepository typeOfEquipmentRepository) {
        this.phoneRepository = phoneRepository;
        this.sizeRepository = sizeRepository;
        this.productRepository = productRepository;
        this.typeOfEquipmentRepository = typeOfEquipmentRepository;
    }

    @PostConstruct
    public void init() {
        log.info("PhoneService initialized");
        Random rnd = new Random();
        List<String> listManufacturer = new ArrayList<>(List.of("Lg", "Beko", "Sharp", "Bosch", "Siemens"));
        List<String> listCountryOfOrigin = new ArrayList<>(List.of("South Korea", "Germany", "Cina", "Japan"));
        List<String> listColor = new ArrayList<>(List.of("Black", "Red", "Green", "White"));
        for (int i = 0; i < 3; i++) {
            Size size = new Size(null, rnd.nextInt(100, 200), rnd.nextInt(100, 200), rnd.nextInt(100, 200));
            TypeOfEquipment typeOfEquipment = new TypeOfEquipment(null, "Phone");
            ProductEntity product = new ProductEntity(null, "Phone", listManufacturer.get(rnd.nextInt(listManufacturer.size())), listCountryOfOrigin.get(rnd.nextInt(listCountryOfOrigin.size())),
                    rnd.nextBoolean(), rnd.nextBoolean(), rnd.nextLong(1000, 10_000), listColor.get(rnd.nextInt(listColor.size())), rnd.nextDouble(1000, 10_000), size, typeOfEquipment);
            PhoneEntity phone = new PhoneEntity(null, product, rnd.nextInt(20, 1000), rnd.nextInt(1, 5));
            sizeRepository.save(size);
            typeOfEquipmentRepository.save(typeOfEquipment);
            productRepository.save(product);
            phoneRepository.save(phone);
        }
    }

    @Override
    public PhoneEntity createPhone(PhoneEntity entity) {
        PhoneEntity phoneEntity = new PhoneEntity();
        ProductEntity productEntity = new ProductEntity();
        TypeOfEquipment typeOfEquipmentEntity = new TypeOfEquipment();
        Size size = new Size();
        size.setWidth(entity.getProduct().getSize().getWidth());
        size.setLength(entity.getProduct().getSize().getLength());
        size.setHeight(entity.getProduct().getSize().getHeight());
        size.setId(null);
        typeOfEquipmentEntity.setName(entity.getProduct().getName());
        typeOfEquipmentEntity.setId(null);
        productEntity.setName(entity.getProduct().getName());
        productEntity.setManufacturer(entity.getProduct().getManufacturer());
        productEntity.setCountryOfOrigin(entity.getProduct().getCountryOfOrigin());
        productEntity.setOrderOnline(entity.getProduct().getOrderOnline());
        productEntity.setInstallmentPlan(entity.getProduct().getInstallmentPlan());
        productEntity.setSerialNumber(entity.getProduct().getSerialNumber());
        productEntity.setColor(entity.getProduct().getColor());
        productEntity.setSize(size);
        productEntity.setTypeOfEquipment(typeOfEquipmentEntity);
        productEntity.setId(null);
        phoneEntity.setProduct(productEntity);
        phoneEntity.setMemoryCapacity(entity.getMemoryCapacity());
        phoneEntity.setNumberOfCameras(entity.getNumberOfCameras());
        phoneEntity.setId(null);
        log.info("Created Phone");
        sizeRepository.save(size);
        typeOfEquipmentRepository.save(typeOfEquipmentEntity);
        productRepository.save(productEntity);
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
