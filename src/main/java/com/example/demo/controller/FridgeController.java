package com.example.demo.controller;

import com.example.demo.entyti.specification.FridgeEntity;
import com.example.demo.service.FridgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fridge")
public class FridgeController {
    private final FridgeService fridgeService;

    public FridgeController(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }
    @PostMapping
    public ResponseEntity<FridgeEntity> createFridge(@RequestBody FridgeEntity entity) {
        return ResponseEntity.ok(fridgeService.createFridge(entity));
    }
    @GetMapping("/fridge/compressor")
    public ResponseEntity<List<FridgeEntity>> findByCompressor(@RequestParam String compressor) {
        return ResponseEntity.ok(fridgeService.findByCompressor(compressor));
    }
    @GetMapping("/fridge/doors")
    public ResponseEntity<List<FridgeEntity>> findByNumberOfDoors(@RequestParam int doors) {
        return ResponseEntity.ok(fridgeService.findByNumberOfDoors(doors));
    }


}
