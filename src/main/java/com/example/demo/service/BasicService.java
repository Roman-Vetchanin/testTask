package com.example.demo.service;

import com.example.demo.entyti.ProductEntity;

import javax.swing.*;
import java.util.List;

public interface BasicService {

    List<ProductEntity> findByName(String name, String type, SortOrder sort);

    List<ProductEntity> findByColor(String color, String name, String type);

    List<ProductEntity> findByPriceRange(double minPrice, double maxPrice, SortOrder sort, String name);

    List<ProductEntity> findBySize(int height, int length, int width, String name);

    List<ProductEntity> findByCountry(String country, String name, SortOrder order);

    List<ProductEntity> findByManufacturer(String manufacturer, String name, SortOrder order);

    List<ProductEntity> findByOrderOnline(Boolean orderOnline, String type);

    List<ProductEntity> findByInstallmentPlan(Boolean installmentPlan, String type);

    List<ProductEntity> productInStock(String type, boolean availability);
}
