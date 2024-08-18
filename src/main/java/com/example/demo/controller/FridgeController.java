package com.example.demo.controller;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.specification.FridgeEntity;
import com.example.demo.service.BasicService;
import com.example.demo.service.FridgeService;
import com.example.demo.service.impl.FridgeServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/products/fridge")
public class FridgeController {
    private final FridgeService fridgeService;
    private final BasicService basicService;

    public FridgeController(FridgeService fridgeService, @Qualifier("fridgeServiceImpl") BasicService basicService) {
        this.fridgeService = fridgeService;
        this.basicService = basicService;
    }

    @PostMapping
    public ResponseEntity<FridgeEntity> createFridge(@RequestBody FridgeEntity entity) {
        return ResponseEntity.ok(fridgeService.createFridge(entity));
    }

    @GetMapping("/compressor")
    public ResponseEntity<List<FridgeEntity>> findByCompressor(@RequestParam String compressor) {
        return ResponseEntity.ok(fridgeService.findByCompressor(compressor));
    }

    @GetMapping("/doors")
    public ResponseEntity<List<FridgeEntity>> findByNumberOfDoors(@RequestParam int doors) {
        return ResponseEntity.ok(fridgeService.findByNumberOfDoors(doors));
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
