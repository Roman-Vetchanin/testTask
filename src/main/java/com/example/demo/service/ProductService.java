package com.example.demo.service;

import com.example.demo.entyti.ProductEntity;
import org.springframework.data.domain.Sort;

import javax.swing.*;
import java.util.List;

public interface ProductService {
    ProductEntity createProduct(ProductEntity entity);

    List<ProductEntity> findByName(String name, SortOrder sort);

    List<ProductEntity> findBySize(int height, int length, int width);

    List<ProductEntity> findByPriceRange(double minPrice, double maxPrice, SortOrder sort);

    ProductEntity findBySerialNumber(long serialNumber);

    List<ProductEntity> findByCounty(String country);

    List<ProductEntity> findByManufacturer(String manufacturer);

    List<ProductEntity> findByOrderOnline(Boolean orderOnline);

    List<ProductEntity> findByInstallmentPlan(Boolean installmentPlan);

    List<ProductEntity> findByType(String type);

    List<ProductEntity> findByColor(String color);
}
