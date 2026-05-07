package com.pnc.gamestore.services;

import com.pnc.gamestore.dto.request.PlatformRequest;
import com.pnc.gamestore.dto.response.PlatformResponse;
import com.pnc.gamestore.exception.DuplicateEntityException;
import com.pnc.gamestore.exception.EntityNotFoundException;
import com.pnc.gamestore.filter.AuthContext;
import com.pnc.gamestore.model.Platforms;
import com.pnc.gamestore.model.User;
import com.pnc.gamestore.repositories.PlatformsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlatformService {

    private final PlatformsRepository platformsRepository;

    public PlatformService(PlatformsRepository platformsRepository) {
        this.platformsRepository = platformsRepository;
    }

    public List<PlatformResponse> getAll() {
        return platformsRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PlatformResponse getById(UUID id) {
        Platforms platform = platformsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Platform", id));
        return toResponse(platform);
    }

    public PlatformResponse createPlatform(PlatformRequest request) {
        if (platformsRepository.findByName(request.getName()).isPresent()) {
            throw new DuplicateEntityException("Platform", "name", request.getName());
        }

        User actor = AuthContext.getUser();
        LocalDateTime now = LocalDateTime.now();

        Platforms platform = new Platforms(request.getName(), request.getCompany());
        platform.setCreatedAt(now);
        platform.setCreatedBy(actor);

        return toResponse(platformsRepository.save(platform));
    }

    public PlatformResponse updatePlatform(UUID id, PlatformRequest request) {
        Platforms existing = platformsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Platform", id));

        if (!existing.getName().equals(request.getName()) &&
                platformsRepository.findByName(request.getName()).isPresent()) {
            throw new DuplicateEntityException("Platform", "name", request.getName());
        }

        User actor = AuthContext.getUser();

        existing.setName(request.getName());
        existing.setCompany(request.getCompany());
        existing.setUpdatedAt(LocalDateTime.now());
        existing.setUpdatedBy(actor);

        return toResponse(platformsRepository.save(existing));
    }

    public void deletePlatform(UUID id) {
        Platforms platform = platformsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Platform", id));
        platformsRepository.delete(platform);
    }

    private PlatformResponse toResponse(Platforms platform) {
        PlatformResponse resp = new PlatformResponse();
        resp.setId(platform.getId());
        resp.setName(platform.getName());
        resp.setCompany(platform.getCompany());
        resp.setCreatedAt(platform.getCreatedAt());
        resp.setUpdatedAt(platform.getUpdatedAt());
        if (platform.getCreatedBy() != null) resp.setCreatedBy(platform.getCreatedBy().getUsername());
        if (platform.getUpdatedBy() != null) resp.setUpdatedBy(platform.getUpdatedBy().getUsername());
        return resp;
    }
}
