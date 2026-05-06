package com.pnc.gamestore.repositories;

import com.pnc.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {
    Optional<Game> findById(UUID id);
    List<Game> findByDev(String dev);
    List<Game> findByDevAndClassification(String dev, String classification);
    List<Game> findByGenres(String genre);
    Optional<Game> findByName(String name);
    boolean existsByName(String name);
}
