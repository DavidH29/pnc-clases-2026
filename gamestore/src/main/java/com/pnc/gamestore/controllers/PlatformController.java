package com.pnc.gamestore.controllers;

import com.pnc.gamestore.dto.request.PlatformRequest;
import com.pnc.gamestore.dto.response.PlatformResponse;
import com.pnc.gamestore.services.PlatformService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/platforms")
public class PlatformController {

    private final PlatformService platformService;

    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping
    public ResponseEntity<List<PlatformResponse>> getAll() {
        return ResponseEntity.ok(platformService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatformResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(platformService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PlatformResponse> createPlatform(@Valid @RequestBody PlatformRequest request) {
        return ResponseEntity.ok(platformService.createPlatform(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlatformResponse> updatePlatform(@PathVariable UUID id,
                                                            @Valid @RequestBody PlatformRequest request) {
        return ResponseEntity.ok(platformService.updatePlatform(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlatform(@PathVariable UUID id) {
        platformService.deletePlatform(id);
        return ResponseEntity.noContent().build();
    }
}
