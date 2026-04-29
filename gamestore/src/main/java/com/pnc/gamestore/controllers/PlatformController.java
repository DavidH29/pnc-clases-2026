package com.pnc.gamestore.controllers;

import com.pnc.gamestore.model.Platforms;
import com.pnc.gamestore.services.PlatformService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("platforms")
public class PlatformController {

    private final PlatformService platformService;

    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping
    public ResponseEntity<List<Platforms>> getAll() {
        return ResponseEntity.ok(platformService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Platforms> getById(@PathVariable UUID id) {
        Platforms p = platformService.getById(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Void> createPlatform(@RequestBody Platforms platform) {
        Platforms saved = platformService.createPlatform(platform);
        return saved != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePlatform(@PathVariable UUID id, @RequestBody Platforms platform) {
        Platforms updated = platformService.updatePlatform(id, platform);
        return updated != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlatform(@PathVariable UUID id) {
        Platforms deleted = platformService.deletePlatform(id);
        return deleted != null ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }
}
