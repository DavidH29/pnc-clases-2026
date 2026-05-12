package com.pnc.gamestore.controllers;

import com.pnc.gamestore.dto.request.ReviewRequest;
import com.pnc.gamestore.dto.response.ReviewResponse;
import com.pnc.gamestore.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByGame(@PathVariable UUID gameId) {
        return ResponseEntity.ok(reviewService.getReviewsByGame(gameId));
    }

    @PostMapping("/game/{gameId}")
    public ResponseEntity<ReviewResponse> createReview(@PathVariable UUID gameId,
                                                        @Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.createReview(gameId, request));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable UUID reviewId,
                                                        @Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.updateReview(reviewId, request));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
