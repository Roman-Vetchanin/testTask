package com.example.demo.controller;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.specification.TvEntity;
import com.example.demo.service.BasicService;
import com.example.demo.service.TvService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("products/tv")
public class TvController {
    private final TvService tvService;
    private final BasicService basicService;

    public TvController(TvService tvService, @Qualifier("tvServiceImpl") BasicService basicService) {
        this.tvService = tvService;
        this.basicService = basicService;
    }
    @PostMapping
    public ResponseEntity<TvEntity> createTv (@RequestBody TvEntity entity) {
        return ResponseEntity.ok(tvService.createTv(entity));
    }
    @GetMapping("/category")
    public ResponseEntity<List<TvEntity>> findByCategory (@RequestParam String category) {
        return ResponseEntity.ok(tvService.findByCategory(category));
    }
    @GetMapping("/technology")
    public ResponseEntity<List<TvEntity>> findByTechnology (@RequestParam String technology) {
        return ResponseEntity.ok(tvService.findByTechnology(technology));
    }
    @GetMapping("/name")
    public ResponseEntity<List<ProductEntity>> findByName(@RequestParam String name, @RequestParam String type, @RequestParam SortOrder order) {
        return ResponseEntity.ok(basicService.findByName(name, type, order));
    }

    @GetMapping("/price")
    public ResponseEntity<List<ProductEntity>> findByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice, @RequestParam String type, SortOrder order) {
        return ResponseEntity.ok(basicService.findByPriceRange(minPrice, maxPrice, order, type));
    }

    @GetMapping("/size")
    public ResponseEntity<List<ProductEntity>> findBySize(@RequestParam int height, @RequestParam int length, @RequestParam int width, @RequestParam String type) {
        return ResponseEntity.ok(basicService.findBySize(height, length, width, type));
    }

    @GetMapping("/country")
    public ResponseEntity<List<ProductEntity>> findByCountry(@RequestParam String country, @RequestParam String type, @RequestParam SortOrder order) {
        return ResponseEntity.ok(basicService.findByCountry(country, type, order));
    }

    @GetMapping("/manufacturer")
    public ResponseEntity<List<ProductEntity>> findByManufacturer(@RequestParam String manufacturer, @RequestParam String type, @RequestParam SortOrder order) {
        return ResponseEntity.ok(basicService.findByManufacturer(manufacturer, type, order));
    }

    @GetMapping("/order-online")
    public ResponseEntity<List<ProductEntity>> findByIsOrderOnline(@RequestParam boolean isOrderOnline, @RequestParam String type) {
        return ResponseEntity.ok(basicService.findByOrderOnline(isOrderOnline, type));
    }

    @GetMapping("/installment-plan")
    public ResponseEntity<List<ProductEntity>> findByIsInstalmentPlan(@RequestParam boolean isInstalmentPlan, @RequestParam String type) {
        return ResponseEntity.ok(basicService.findByInstallmentPlan(isInstalmentPlan, type));
    }

    @GetMapping("in-stock")
    public ResponseEntity<List<ProductEntity>> productInStock(@RequestParam boolean inStock, @RequestParam String type) {
        return ResponseEntity.ok(basicService.productInStock(type, inStock));
    }

    @GetMapping("/color")
    public ResponseEntity<List<ProductEntity>> findByColor(@RequestParam String color, @RequestParam String name, @RequestParam String type) {
        return ResponseEntity.ok(basicService.findByColor(color, name, type));
    }
}
