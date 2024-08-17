package com.example.demo.controller;

import com.example.demo.entyti.specification.TvEntity;
import com.example.demo.service.TvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tv")
public class TvController {
    private final TvService tvService;

    public TvController(TvService tvService) {
        this.tvService = tvService;
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
}
