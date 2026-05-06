package com.pnc.gamestore.controllers;

import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.model.enums.Genre;
import com.pnc.gamestore.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAll() {
        return ResponseEntity.ok(gameService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getById(@PathVariable UUID id) {
        Game game = gameService.getById(id);
        if (game == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(game);
    }

    @PostMapping
    public ResponseEntity<Void> createGame(@RequestBody Game game) {
        Game saved = gameService.createGame(game);
        if (saved == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGame(@PathVariable UUID id, @RequestBody Game game) {
        Game updated = gameService.updateGame(id, game);
        if (updated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable UUID id) {
        Game deleted = gameService.deleteGame(id);
        if (deleted == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }

    @QueryMapping
    public List<Game> allGames() {
        return gameService.getAll();
    }

    @QueryMapping
    public Game gameById(@Argument UUID id) {
        return gameService.getById(id);
    }
}
