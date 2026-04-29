package com.pnc.gamestore.controllers;

import com.pnc.gamestore.model.Reviews;
import com.pnc.gamestore.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Reviews>> getReviewsByGame(@PathVariable UUID gameId) {
        return ResponseEntity.ok(reviewService.getReviewsByGame(gameId));
    }

    @PostMapping("/game/{gameId}")
    public ResponseEntity<Void> createReview(@PathVariable UUID gameId, @RequestBody Reviews review) {
        Reviews saved = reviewService.createReview(gameId, review);
        return saved != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Void> updateReview(@PathVariable UUID reviewId, @RequestBody Reviews review) {
        Reviews updated = reviewService.updateReview(reviewId, review);
        return updated != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID reviewId) {
        Reviews deleted = reviewService.deleteReview(reviewId);
        return deleted != null ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }
}
