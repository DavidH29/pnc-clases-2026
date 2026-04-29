package com.pnc.gamestore.services;

import com.pnc.gamestore.model.Platforms;
import com.pnc.gamestore.repositories.PlatformsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlatformService {

    private final PlatformsRepository platformsRepository;

    public PlatformService(PlatformsRepository platformsRepository) {
        this.platformsRepository = platformsRepository;
    }

    public List<Platforms> getAll() {
        return platformsRepository.findAll();
    }

    public Platforms getById(UUID id) {
        return platformsRepository.findById(id).orElse(null);
    }

    public Platforms createPlatform(Platforms platform) {
        if (platform.getName() == null || platform.getName().isBlank() ||
                platform.getCompany() == null || platform.getCompany().isBlank()) {
            return null;
        }
        if (platformsRepository.findByName(platform.getName()).isPresent()) {
            return null;
        }
        return platformsRepository.save(platform);
    }

    public Platforms updatePlatform(UUID id, Platforms platformData) {
        Platforms existing = getById(id);
        if (existing == null) return null;
        if (platformData.getName() == null || platformData.getName().isBlank() ||
                platformData.getCompany() == null || platformData.getCompany().isBlank()) {
            return null;
        }
        existing.setName(platformData.getName());
        existing.setCompany(platformData.getCompany());
        return platformsRepository.save(existing);
    }

    public Platforms deletePlatform(UUID id) {
        Platforms p = getById(id);
        if (p != null) {
            platformsRepository.delete(p);
            return p;
        }
        return null;
    }
}
