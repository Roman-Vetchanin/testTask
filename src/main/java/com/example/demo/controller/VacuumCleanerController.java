package com.example.demo.controller;

import com.example.demo.entyti.specification.VacuumCleanerEntity;
import com.example.demo.service.VacuumCleanerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacuum-cleaner")
public class VacuumCleanerController {
    private final VacuumCleanerService vacuumCleanerService;

    public VacuumCleanerController(VacuumCleanerService vacuumCleanerService) {
        this.vacuumCleanerService = vacuumCleanerService;
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
}
