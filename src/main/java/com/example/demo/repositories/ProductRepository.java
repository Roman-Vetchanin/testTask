package com.example.demo.repositories;

import com.example.demo.entyti.ProductEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByNameIgnoreCase(String name);

    ProductEntity findBySerialNumber(long serialNumber);

    List<ProductEntity> findByCountryOfOriginIgnoreCase(String country);

    List<ProductEntity> findByManufacturerIgnoreCase(String manufacturer);

    List<ProductEntity> findByOrderOnline(Boolean orderOnline);

    List<ProductEntity> findByInstallmentPlan(Boolean installmentPlan);

    List<ProductEntity> findByTypeOfEquipment_NameIgnoreCase(String typeOfEquipment);

    List<ProductEntity> findByColorIgnoreCase(String color);

    List<ProductEntity> findByPriceBetween(double priceMin, double priceMax);

    List<ProductEntity> findBySize_HeightBeforeAndSize_LengthBeforeAndSize_WidthBefore(int height, int length, int width);
}