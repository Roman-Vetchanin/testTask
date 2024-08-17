package com.example.demo.service.impl;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.equipment.TypeOfEquipment;
import com.example.demo.entyti.specification.FridgeEntity;
import com.example.demo.entyti.specification.PcEntity;
import com.example.demo.entyti.specification.Size;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repositories.PcRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SizeRepository;
import com.example.demo.repositories.TypeOfEquipmentRepository;
import com.example.demo.service.PcService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class PcServiceImpl implements PcService {
    private final PcRepository pcRepository;
    private final TypeOfEquipmentRepository typeOfEquipmentRepository;
    private final SizeRepository sizeRepository;
    private final ProductRepository productRepository;

    public PcServiceImpl(PcRepository pcRepository, TypeOfEquipmentRepository typeOfEquipmentRepository, SizeRepository sizeRepository, ProductRepository productRepository) {
        this.pcRepository = pcRepository;
        this.typeOfEquipmentRepository = typeOfEquipmentRepository;
        this.sizeRepository = sizeRepository;
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        log.info("Initializing PcService");
        Random rnd = new Random();
        List<String> listManufacturer = new ArrayList<>(List.of("Lg", "Beko", "Sharp", "Bosch", "Siemens"));
        List<String> listCountryOfOrigin = new ArrayList<>(List.of("South Korea", "Germany", "Cina", "Japan"));
        List<String> listColor = new ArrayList<>(List.of("Black", "Red", "Green", "White"));
        List<String> listProcessorType = new ArrayList<>(List.of("CPU", "GPU"));
        List<String> listCategory = new ArrayList<>(List.of("Game", "Office", "Server"));
        for (int i = 0; i < 3; i++) {
            Size size = new Size(null, rnd.nextInt(100,200), rnd.nextInt(100,200), rnd.nextInt(100,200));
            TypeOfEquipment typeOfEquipment = new TypeOfEquipment(null, "PC");
            ProductEntity product = new ProductEntity(null, "PC", listManufacturer.get(rnd.nextInt(listManufacturer.size())), listCountryOfOrigin.get(rnd.nextInt(listCountryOfOrigin.size())),
                    rnd.nextBoolean(),rnd.nextBoolean(), rnd.nextLong(1000,10_000), listColor.get(rnd.nextInt(listColor.size())), rnd.nextDouble(1000,10_000), size, typeOfEquipment);
            PcEntity pcEntity = new PcEntity(null, product, listProcessorType.get(rnd.nextInt(listProcessorType.size())), listCategory.get(rnd.nextInt(listCategory.size())));
            sizeRepository.save(size);
            typeOfEquipmentRepository.save(typeOfEquipment);
            productRepository.save(product);
            pcRepository.save(pcEntity);
        }
    }

    @Override
    public PcEntity createPc(PcEntity entity) {
        PcEntity pcEntity = new PcEntity();
        ProductEntity productEntity = new ProductEntity();
        TypeOfEquipment typeOfEquipment = new TypeOfEquipment();
        typeOfEquipment.setName(entity.getProduct().getTypeOfEquipment().getName());
        Size size = new Size();
        size.setWidth(entity.getProduct().getSize().getWidth());
        size.setLength(entity.getProduct().getSize().getLength());
        size.setHeight(entity.getProduct().getSize().getHeight());
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
        productRepository.save(productEntity);
        typeOfEquipmentRepository.save(typeOfEquipment);
        sizeRepository.save(size);
        return pcRepository.save(pcEntity);
    }

    @Override
    public List<PcEntity> findByCategory(String category) {
        List<PcEntity> findList = pcRepository.findByCategory(category);
        if (findList.isEmpty()) {
            log.warn("Cannot find Pc");
            throw new ProductNotFoundException("There are no products in this category " + category);
        }
        log.info("Pc found");
        return findList;
    }

    @Override
    public List<PcEntity> findByProcessor(String processor) {
        List<PcEntity> findList = pcRepository.findByProcessorType(processor);
        if (findList.isEmpty()) {
            throw new ProductNotFoundException("A computer with this " + processor + " was not found");
        }
        return findList;
    }
}
