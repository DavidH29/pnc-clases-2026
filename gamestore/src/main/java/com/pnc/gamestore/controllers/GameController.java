package com.pnc.gamestore.controllers;

import com.pnc.gamestore.dto.request.GameRequest;
import com.pnc.gamestore.dto.response.GameResponse;
import com.pnc.gamestore.services.GameService;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<GameResponse>> getAll() {
        return ResponseEntity.ok(gameService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(gameService.getById(id));
    }

    @PostMapping
    public ResponseEntity<GameResponse> createGame(@Valid @RequestBody GameRequest request) {
        return ResponseEntity.ok(gameService.createGame(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponse> updateGame(@PathVariable UUID id,
                                                    @Valid @RequestBody GameRequest request) {
        return ResponseEntity.ok(gameService.updateGame(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable UUID id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

    @QueryMapping
    public List<GameResponse> allGames() {
        return gameService.getAll();
    }

    @QueryMapping
    public GameResponse gameById(@Argument UUID id) {
        return gameService.getById(id);
    }
}
