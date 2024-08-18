package com.example.demo.service;

import com.example.demo.entyti.ProductEntity;

import javax.swing.*;
import java.util.List;

public interface ProductService {
    ProductEntity createProduct(ProductEntity entity);

    List<ProductEntity> findByName(String name, SortOrder sort);

    List<ProductEntity> findByColor (String color, SortOrder sort);

    List<ProductEntity> findByTypeOfEquipment(String typeOfEquipment, SortOrder order);

    ProductEntity findBySerialNumber(long serialNumber);

    List<ProductEntity> findAll(int pageSize, int pageCount, SortOrder sortOrder, SortOrder sortOrderPrice);

    List<ProductEntity> findByCountry(String country, String name);

    List<ProductEntity> findByManufacturer(String manufacturer);

    List<ProductEntity> findByOrderOnline(Boolean orderOnline);

    List<ProductEntity> findByInstallmentPlan(Boolean installmentPlan);
}
