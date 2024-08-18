package com.example.demo.controller;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.service.ProductService;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity entity) {
        return ResponseEntity.ok(productService.createProduct(entity));
    }

    @GetMapping("/name")
    public ResponseEntity<List<ProductEntity>> findByName(@RequestParam String name, @RequestParam SortOrder order) {
        return ResponseEntity.ok(productService.findByName(name, order));
    }

    @GetMapping("/color")
    public ResponseEntity<List<ProductEntity>> findByColor(@RequestParam String color, @RequestParam SortOrder order) {
        return ResponseEntity.ok(productService.findByColor(color, order));
    }

    @GetMapping("/type")
    public ResponseEntity<List<ProductEntity>> findByEquipmentType(@RequestParam String type, @RequestParam SortOrder order) {
        return ResponseEntity.ok(productService.findByTypeOfEquipment(type, order));
    }

    @GetMapping("/serial-number")
    public ResponseEntity<ProductEntity> findBySerialNumber(@RequestParam long serialNumber) {
        return ResponseEntity.ok(productService.findBySerialNumber(serialNumber));
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> findAllProducts(@RequestParam @Min(1) int page, @RequestParam @Min(1) int size, @RequestParam SortOrder order, @RequestParam SortOrder orderPrice) {
        return ResponseEntity.ok(productService.findAll(page, size, order, orderPrice));
    }

    @GetMapping("/country")
    public ResponseEntity<List<ProductEntity>> findByCountry(@RequestParam String country, @RequestParam String name) {
        return ResponseEntity.ok(productService.findByCountry(country, name));
    }

    @GetMapping("/manufacturer")
    public ResponseEntity<List<ProductEntity>> findByManufacturer(@RequestParam String manufacturer) {
        return ResponseEntity.ok(productService.findByManufacturer(manufacturer));
    }

    @GetMapping("/order-online")
    public ResponseEntity<List<ProductEntity>> findByOrderOnline(@RequestParam boolean orderOnline) {
        return ResponseEntity.ok(productService.findByOrderOnline(orderOnline));
    }

    @GetMapping("/installment-plan")
    public ResponseEntity<List<ProductEntity>> findByInstallmentPlan(@RequestParam boolean installmentPlan) {
        return ResponseEntity.ok(productService.findByInstallmentPlan(installmentPlan));
    }

}
