package com.pnc.gamestore.services;

import com.pnc.gamestore.dto.request.GameRequest;
import com.pnc.gamestore.dto.response.GameResponse;
import com.pnc.gamestore.exception.BusinessRuleException;
import com.pnc.gamestore.exception.DuplicateEntityException;
import com.pnc.gamestore.exception.EntityNotFoundException;
import com.pnc.gamestore.filter.AuthContext;
import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.model.GameDetails;
import com.pnc.gamestore.model.Platforms;
import com.pnc.gamestore.model.User;
import com.pnc.gamestore.model.enums.Classification;
import com.pnc.gamestore.repositories.GameRepository;
import com.pnc.gamestore.repositories.PlatformsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlatformsRepository platformsRepository;

    public GameService(GameRepository gameRepository, PlatformsRepository platformsRepository) {
        this.gameRepository = gameRepository;
        this.platformsRepository = platformsRepository;
    }

    public List<GameResponse> getAll() {
        return gameRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public GameResponse getById(UUID id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game", id));
        return toResponse(game);
    }

    public GameResponse createGame(GameRequest request) {
        if (gameRepository.existsByName(request.getName())) {
            throw new DuplicateEntityException("Game", "name", request.getName());
        }

        List<Platforms> platforms = resolvePlatforms(request.getPlatformIds());

        if (request.getClassification() == Classification.M) {
            boolean hasNintendo = platforms.stream()
                    .anyMatch(p -> "nintendo".equalsIgnoreCase(p.getCompany()));
            if (hasNintendo) {
                throw new BusinessRuleException(
                        "M-rated games cannot be assigned to Nintendo platforms");
            }
        }

        if (request.getDetails() != null && request.getDetails().getPublishYear() != null) {
            int currentYear = Year.now().getValue();
            if (request.getDetails().getPublishYear() > currentYear) {
                throw new BusinessRuleException("Publish year cannot be in the future");
            }
        }

        User actor = AuthContext.getUser();
        LocalDateTime now = LocalDateTime.now();

        Game game = new Game(
                request.getName(),
                request.getClassification(),
                request.getGenres() != null ? request.getGenres() : new ArrayList<>(),
                request.getDev()
        );
        game.setPlatforms(platforms);
        game.setCreatedAt(now);
        game.setCreatedBy(actor);

        if (request.getDetails() != null) {
            GameDetails details = new GameDetails(
                    request.getDetails().getAbout(),
                    request.getDetails().getPublishYear()
            );
            details.setGame(game);
            details.setCreatedAt(now);
            details.setCreatedBy(actor);
            game.setDetails(details);
        }

        return toResponse(gameRepository.save(game));
    }

    public GameResponse updateGame(UUID id, GameRequest request) {
        Game existing = gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game", id));

        if (!existing.getName().equals(request.getName()) &&
                gameRepository.existsByName(request.getName())) {
            throw new DuplicateEntityException("Game", "name", request.getName());
        }

        List<Platforms> platforms = resolvePlatforms(request.getPlatformIds());

        if (request.getClassification() == Classification.M) {
            boolean hasNintendo = platforms.stream()
                    .anyMatch(p -> "nintendo".equalsIgnoreCase(p.getCompany()));
            if (hasNintendo) {
                throw new BusinessRuleException(
                        "M-rated games cannot be assigned to Nintendo platforms");
            }
        }

        if (request.getDetails() != null && request.getDetails().getPublishYear() != null) {
            int currentYear = Year.now().getValue();
            if (request.getDetails().getPublishYear() > currentYear) {
                throw new BusinessRuleException("Publish year cannot be in the future");
            }
        }

        User actor = AuthContext.getUser();
        LocalDateTime now = LocalDateTime.now();

        existing.setName(request.getName());
        existing.setDev(request.getDev());
        existing.setClassification(request.getClassification());
        existing.setGenres(request.getGenres() != null ? request.getGenres() : new ArrayList<>());
        existing.getPlatforms().clear();
        existing.getPlatforms().addAll(platforms);
        existing.setUpdatedAt(now);
        existing.setUpdatedBy(actor);

        if (request.getDetails() != null) {
            if (existing.getDetails() == null) {
                GameDetails details = new GameDetails(
                        request.getDetails().getAbout(),
                        request.getDetails().getPublishYear()
                );
                details.setGame(existing);
                details.setCreatedAt(now);
                details.setCreatedBy(actor);
                existing.setDetails(details);
            } else {
                existing.getDetails().setAbout(request.getDetails().getAbout());
                existing.getDetails().setPublishYear(request.getDetails().getPublishYear());
                existing.getDetails().setUpdatedAt(now);
                existing.getDetails().setUpdatedBy(actor);
            }
        } else {
            existing.setDetails(null);
        }

        return toResponse(gameRepository.save(existing));
    }

    public void deleteGame(UUID id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game", id));
        gameRepository.delete(game);
    }

    private List<Platforms> resolvePlatforms(List<UUID> platformIds) {
        Set<UUID> unique = new HashSet<>(platformIds);
        if (unique.size() != platformIds.size()) {
            throw new BusinessRuleException("Duplicate platform IDs provided");
        }
        List<Platforms> result = new ArrayList<>();
        for (UUID pid : platformIds) {
            Platforms p = platformsRepository.findById(pid)
                    .orElseThrow(() -> new EntityNotFoundException("Platform", pid));
            result.add(p);
        }
        return result;
    }

    public GameResponse toResponse(Game game) {
        GameResponse resp = new GameResponse();
        resp.setId(game.getId());
        resp.setName(game.getName());
        resp.setClassification(game.getClassification());
        resp.setGenres(game.getGenres());
        resp.setDev(game.getDev());
        resp.setCreatedAt(game.getCreatedAt());
        resp.setUpdatedAt(game.getUpdatedAt());
        if (game.getCreatedBy() != null) resp.setCreatedBy(game.getCreatedBy().getUsername());
        if (game.getUpdatedBy() != null) resp.setUpdatedBy(game.getUpdatedBy().getUsername());

        if (game.getDetails() != null) {
            GameResponse.GameDetailsResponse det = new GameResponse.GameDetailsResponse();
            det.setAbout(game.getDetails().getAbout());
            det.setPublishYear(game.getDetails().getPublishYear());
            resp.setDetails(det);
        }

        if (game.getPlatforms() != null) {
            resp.setPlatforms(game.getPlatforms().stream()
                    .map(Platforms::getName)
                    .collect(Collectors.toList()));
        }

        return resp;
    }
}
