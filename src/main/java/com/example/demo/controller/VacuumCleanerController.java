package com.example.demo.controller;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.specification.VacuumCleanerEntity;
import com.example.demo.service.BasicService;
import com.example.demo.service.VacuumCleanerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/products/vacuum-cleaner")
public class VacuumCleanerController {
    private final VacuumCleanerService vacuumCleanerService;
    private final BasicService basicService;

    public VacuumCleanerController(VacuumCleanerService vacuumCleanerService, @Qualifier("vacuumCleanerServiceImpl") BasicService basicService) {
        this.vacuumCleanerService = vacuumCleanerService;
        this.basicService = basicService;
    }
    @PostMapping
    public ResponseEntity<VacuumCleanerEntity> createVacuumCleaner (@RequestBody VacuumCleanerEntity entity) {
        return ResponseEntity.ok(vacuumCleanerService.createVacuumCleaner(entity));
    }
    @GetMapping("/volume-dust")
    public ResponseEntity<List<VacuumCleanerEntity>> findByVolumeDust (@RequestParam int volumeDust) {
        return ResponseEntity.ok(vacuumCleanerService.findByDustContainerVolume(volumeDust));
    }
    @GetMapping("/modes")
    public ResponseEntity<List<VacuumCleanerEntity>> findByNumberOfModes (@RequestParam int numberOfModes) {
        return ResponseEntity.ok(vacuumCleanerService.findByNumberOfModes(numberOfModes));
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
