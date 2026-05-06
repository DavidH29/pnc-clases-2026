package com.pnc.gamestore.services;

import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.model.GameDetails;
import com.pnc.gamestore.model.Platforms;
import com.pnc.gamestore.model.enums.Classification;
import com.pnc.gamestore.model.enums.Genre;
import com.pnc.gamestore.repositories.GameRepository;
import com.pnc.gamestore.repositories.PlatformsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    public Game getById(UUID id) {
        return gameRepository.findById(id).orElse(null);
    }

    public List<Game> findByGenre(Genre genre) {
        return gameRepository.findAll().stream()
                .filter(g -> g.getGenres().contains(genre))
                .collect(Collectors.toList());
    }

    public Game createGame(Game game) {
        if (game.getName() == null || game.getName().isBlank() ||
                gameRepository.existsByName(game.getName())) {
            return null;
        }

        if (game.getDev() == null || game.getDev().isBlank()) {
            return null;
        }

        if (game.getClassification() == null) {
            return null;
        }

        if (game.getPlatforms() == null || game.getPlatforms().isEmpty()) {
            return null;
        }

        Set<UUID> ids = new HashSet<>();
        for (Platforms p : game.getPlatforms()) {
            if (p.getId() == null || !ids.add(p.getId())) {
                return null;
            }
        }

        List<Platforms> managedPlatforms = new ArrayList<>();
        for (Platforms p : game.getPlatforms()) {
            Optional<Platforms> opt = platformsRepository.findById(p.getId());
            if (opt.isEmpty()) {
                return null;
            }
            managedPlatforms.add(opt.get());
        }
        game.setPlatforms(managedPlatforms);

        if (game.getClassification() == Classification.M) {
            boolean nintendo = managedPlatforms.stream()
                    .anyMatch(p -> p.getCompany() != null
                            && p.getCompany().equalsIgnoreCase("nintendo"));
            if (nintendo) {
                return null;
            }
        }

        if (game.getDetails() != null) {
            GameDetails details = game.getDetails();
            if (details.getPublishYear() != null) {
                int currentYear = Year.now().getValue();
                if (details.getPublishYear() < 1975 || details.getPublishYear() > currentYear) {
                    return null;
                }
            }
        }

        return gameRepository.save(game);
    }

    public Game updateGame(UUID id, Game gameData) {
        Optional<Game> existingOpt = gameRepository.findById(id);
        if (existingOpt.isEmpty()) {
            return null;
        }
        Game existing = existingOpt.get();

        if (!existing.getName().equals(gameData.getName())) {
            if (gameRepository.existsByName(gameData.getName())) {
                return null;
            }
        }

        if (gameData.getName() == null || gameData.getName().isBlank() ||
                gameData.getDev() == null || gameData.getDev().isBlank() ||
                gameData.getClassification() == null) {
            return null;
        }

        if (gameData.getPlatforms() != null && !gameData.getPlatforms().isEmpty()) {
            Set<UUID> ids = new HashSet<>();
            for (Platforms p : gameData.getPlatforms()) {
                if (p.getId() == null || !ids.add(p.getId())) {
                    return null;
                }
            }
            List<Platforms> managed = new ArrayList<>();
            for (Platforms p : gameData.getPlatforms()) {
                Optional<Platforms> opt = platformsRepository.findById(p.getId());
                if (opt.isEmpty()) {
                    return null;
                }
                managed.add(opt.get());
            }
            if (gameData.getClassification() == Classification.M) {
                boolean nintendo = managed.stream()
                        .anyMatch(p -> p.getCompany() != null && p.getCompany().equalsIgnoreCase("nintendo"));
                if (nintendo) {
                    return null;
                }
            }
            existing.getPlatforms().clear();
            existing.getPlatforms().addAll(managed);
        } else {
            return null;
        }

        if (gameData.getDetails() != null) {
            GameDetails newDetail = gameData.getDetails();
            if (newDetail.getPublishYear() != null) {
                int currentYear = Year.now().getValue();
                if (newDetail.getPublishYear() < 1975 || newDetail.getPublishYear() > currentYear) {
                    return null;
                }
            }
            if (existing.getDetails() == null) {
                existing.setDetails(newDetail);
                newDetail.setGame(existing);
            } else {
                existing.getDetails().setAbout(newDetail.getAbout());
                existing.getDetails().setPublishYear(newDetail.getPublishYear());
            }
        } else {
            existing.setDetails(null);
        }

        existing.setName(gameData.getName());
        existing.setDev(gameData.getDev());
        existing.setClassification(gameData.getClassification());
        existing.setGenres(gameData.getGenres());

        return gameRepository.save(existing);
    }


    public Game deleteGame(UUID id) {
        Optional<Game> gameOpt = gameRepository.findById(id);
        if (gameOpt.isPresent()) {
            gameRepository.delete(gameOpt.get());
            return gameOpt.get();
        }
        return null;
    }
}
