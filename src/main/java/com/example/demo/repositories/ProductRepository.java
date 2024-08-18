package com.example.demo.repositories;

import com.example.demo.entyti.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByNameIgnoreCaseAndTypeOfEquipment_NameIgnoreCase(String name, String type);

    List<ProductEntity> findByNameIgnoreCase(String name);

    ProductEntity findBySerialNumber(long serialNumber);

    List<ProductEntity> findByColorIgnoreCase(String color);

    List<ProductEntity> findByCountryOfOriginIgnoreCaseAndTypeOfEquipment_NameIgnoreCase(String country, String name);

    List<ProductEntity> findByManufacturerIgnoreCaseAndTypeOfEquipment_NameIgnoreCase(String manufacturer, String name);

    List<ProductEntity> findByManufacturerIgnoreCase(String manufacturer);

    List<ProductEntity> findByOrderOnline(Boolean orderOnline);

    List<ProductEntity> findByOrderOnlineAndTypeOfEquipment_NameIgnoreCase(Boolean orderOnline, String name);

    List<ProductEntity> findByInstallmentPlan(Boolean installmentPlan);

    List<ProductEntity> findByInstallmentPlanAndTypeOfEquipment_NameIgnoreCase(Boolean installmentPlan, String type);

    List<ProductEntity> findByTypeOfEquipment_NameIgnoreCase(String typeOfEquipment);

    List<ProductEntity> findByColorIgnoreCaseAndNameIgnoreCaseAndTypeOfEquipment_NameIgnoreCase(String color, String name, String typeOfEquipment);

    List<ProductEntity> findByPriceBetweenAndTypeOfEquipment_NameIgnoreCase(double priceMin, double priceMax, String name);

    List<ProductEntity> findBySize_HeightBeforeAndSize_LengthBeforeAndSize_WidthBeforeAndTypeOfEquipment_Name(int height, int length, int width, String name);

    List<ProductEntity> findByNameIgnoreCaseAndProductAvailability(String name, boolean availability);

    List<ProductEntity> findByTypeOfEquipment_NameIgnoreCaseAndProductAvailability(String name, boolean availability);
}