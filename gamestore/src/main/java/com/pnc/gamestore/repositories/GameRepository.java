package com.pnc.gamestore.repositories;

import com.pnc.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    public Optional<Game> findById(Integer id);
    public List<Game> findByDev(String dev);
    public List<Game> findByDevAndClassification(String dev, String classification);
    public List<Game> findByGenre(String genre);
}
