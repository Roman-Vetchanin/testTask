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
import com.example.demo.service.FridgeService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class FridgeServiceImpl implements FridgeService {

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
            TypeOfEquipment typeOfEquipment = new TypeOfEquipment(null, "Refrigerator");
            ProductEntity productEntity = new ProductEntity(null, "Refrigerator", listManufacturer.get(random.nextInt(listManufacturer.size())),
                    listCountryOfOrigin.get(random.nextInt(listCountryOfOrigin.size())), random.nextBoolean(), random.nextBoolean(),
                    random.nextLong(1000, 10_00000), listColor.get(random.nextInt(listColor.size())), random.nextDouble(100, 10_000), size, typeOfEquipment);
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
}
