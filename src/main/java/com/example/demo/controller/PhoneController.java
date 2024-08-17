package com.example.demo.controller;

import com.example.demo.entyti.specification.PhoneEntity;
import com.example.demo.service.PhoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phone")
public class PhoneController {
    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }
    @PostMapping
    public ResponseEntity<PhoneEntity> createPhone(@RequestBody PhoneEntity entity) {
        return ResponseEntity.ok(phoneService.createPhone(entity));
    }

    @GetMapping("/camera")
    public ResponseEntity<List<PhoneEntity>> numberOfCameras(@RequestBody int count) {
        return ResponseEntity.ok(phoneService.findByNumberOfCameras(count));
    }
    @GetMapping("/memory")
    public ResponseEntity<List<PhoneEntity>> memoryCapacity(@RequestBody int capacity) {
        return ResponseEntity.ok(phoneService.findByMemoryCapacity(capacity));
    }
}
