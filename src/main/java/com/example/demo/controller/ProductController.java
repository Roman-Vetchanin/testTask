package com.example.demo.controller;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.service.ProductService;
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
    @GetMapping("/find/name")
    public ResponseEntity<List<ProductEntity>> findByName (@RequestParam String name, @RequestParam SortOrder sortOrder) {
        return ResponseEntity.ok(productService.findByName(name, sortOrder));
    }
    @GetMapping("/find/size")
    public ResponseEntity<List<ProductEntity>> findBySize (@RequestParam int height, @RequestParam int length, @RequestParam int width) {
        return ResponseEntity.ok(productService.findBySize(height, length, width));
    }
    @GetMapping("/find/price-range")
    public ResponseEntity<List<ProductEntity>> findByPriceRange (@RequestParam double minPrice, @RequestParam double maxPrice, @RequestParam SortOrder order) {
        return ResponseEntity.ok(productService.findByPriceRange(minPrice, maxPrice, order));
    }
    @GetMapping("/find/serial-number")
    public ResponseEntity<ProductEntity> findBySerialNumber (@RequestParam long serialNumber) {
        return ResponseEntity.ok(productService.findBySerialNumber(serialNumber));
    }

    @GetMapping("/find/color")
    public ResponseEntity<List<ProductEntity>> findByColor (@RequestParam String color) {
        return ResponseEntity.ok(productService.findByColor(color));
    }
    @GetMapping("/find/type/{type}")
    public ResponseEntity<List<ProductEntity>> findByEquipmentType (@PathVariable String type) {
        return ResponseEntity.ok(productService.findByType(type));
    }
}
