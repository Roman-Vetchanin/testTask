package com.example.demo.controller;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.specification.PcEntity;
import com.example.demo.service.BasicService;
import com.example.demo.service.PcService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/products/pc")
public class PcController {
    private final PcService service;
    private final BasicService basicService;

    public PcController(PcService service, @Qualifier("pcServiceImpl") BasicService basicService) {
        this.service = service;
        this.basicService = basicService;
    }
    @PostMapping
    public ResponseEntity<PcEntity> createPc(@RequestBody PcEntity entity) {
        return ResponseEntity.ok(service.createPc(entity));
    }
    @GetMapping("/category")
    private ResponseEntity<List<PcEntity>> categories (@RequestParam String category) {
        return ResponseEntity.ok(service.findByCategory(category));
    }
    @GetMapping("/processor")
    private ResponseEntity<List<PcEntity>> processor (@RequestParam String processorType) {
        return ResponseEntity.ok(service.findByProcessor(processorType));
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
