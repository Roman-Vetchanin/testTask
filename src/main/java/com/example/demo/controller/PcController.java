package com.example.demo.controller;

import com.example.demo.entyti.specification.PcEntity;
import com.example.demo.service.PcService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pc")
public class PcController {
    private final PcService service;

    public PcController(PcService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<PcEntity> createPc(@RequestBody PcEntity entity) {
        return ResponseEntity.ok(service.createPc(entity));
    }
    @GetMapping("/pc/category")
    private ResponseEntity<List<PcEntity>> categories (@RequestParam String category) {
        return ResponseEntity.ok(service.findByCategory(category));
    }
    @GetMapping("/pc/processor")
    private ResponseEntity<List<PcEntity>> processor (@RequestParam String processorType) {
        return ResponseEntity.ok(service.findByProcessor(processorType));
    }
}
