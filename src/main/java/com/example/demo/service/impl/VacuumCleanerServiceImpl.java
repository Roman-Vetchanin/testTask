package com.example.demo.service.impl;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.equipment.TypeOfEquipment;
import com.example.demo.entyti.specification.Size;
import com.example.demo.entyti.specification.TvEntity;
import com.example.demo.entyti.specification.VacuumCleanerEntity;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SizeRepository;
import com.example.demo.repositories.TypeOfEquipmentRepository;
import com.example.demo.repositories.VacuumCleanerRepository;
import com.example.demo.service.VacuumCleanerService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class VacuumCleanerServiceImpl implements VacuumCleanerService {
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
                    rnd.nextBoolean(),rnd.nextBoolean(), rnd.nextLong(1000,10_000), listColor.get(rnd.nextInt(listColor.size())), rnd.nextDouble(1000,10_000), size, typeOfEquipment);
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
}
