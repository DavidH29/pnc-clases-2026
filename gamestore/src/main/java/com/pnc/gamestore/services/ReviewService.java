package com.pnc.gamestore.services;

import com.pnc.gamestore.dto.request.ReviewRequest;
import com.pnc.gamestore.dto.response.ReviewResponse;
import com.pnc.gamestore.exception.BusinessRuleException;
import com.pnc.gamestore.exception.EntityNotFoundException;
import com.pnc.gamestore.filter.AuthContext;
import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.model.Reviews;
import com.pnc.gamestore.model.User;
import com.pnc.gamestore.repositories.GameRepository;
import com.pnc.gamestore.repositories.ReviewsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewsRepository reviewsRepository;
    private final GameRepository gameRepository;

    public ReviewService(ReviewsRepository reviewsRepository, GameRepository gameRepository) {
        this.reviewsRepository = reviewsRepository;
        this.gameRepository = gameRepository;
    }

    public List<ReviewResponse> getReviewsByGame(UUID gameId) {
        if (!gameRepository.existsById(gameId)) {
            throw new EntityNotFoundException("Game", gameId);
        }
        return reviewsRepository.findByGameId(gameId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ReviewResponse createReview(UUID gameId, ReviewRequest request) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game", gameId));

        User actor = AuthContext.getUser();

        if (reviewsRepository.existsByGameIdAndUserId(gameId, actor.getId())) {
            throw new BusinessRuleException(
                    "User '" + actor.getUsername() + "' has already reviewed this game");
        }

        LocalDateTime now = LocalDateTime.now();

        Reviews review = new Reviews();
        review.setUser(actor);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setGame(game);
        review.setCreatedAt(now);
        review.setCreatedBy(actor);

        return toResponse(reviewsRepository.save(review));
    }

    public ReviewResponse updateReview(UUID reviewId, ReviewRequest request) {
        Reviews existing = reviewsRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review", reviewId));

        User actor = AuthContext.getUser();

        existing.setRating(request.getRating());
        existing.setComment(request.getComment());
        existing.setUpdatedAt(LocalDateTime.now());
        existing.setUpdatedBy(actor);

        return toResponse(reviewsRepository.save(existing));
    }

    public void deleteReview(UUID reviewId) {
        Reviews review = reviewsRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review", reviewId));
        reviewsRepository.delete(review);
    }

    private ReviewResponse toResponse(Reviews review) {
        ReviewResponse resp = new ReviewResponse();
        resp.setId(review.getId());
        resp.setRating(review.getRating());
        resp.setComment(review.getComment());
        if (review.getUser() != null) resp.setUsername(review.getUser().getUsername());
        if (review.getGame() != null) resp.setGameId(review.getGame().getId());
        resp.setCreatedAt(review.getCreatedAt());
        resp.setUpdatedAt(review.getUpdatedAt());
        if (review.getCreatedBy() != null) resp.setCreatedBy(review.getCreatedBy().getUsername());
        if (review.getUpdatedBy() != null) resp.setUpdatedBy(review.getUpdatedBy().getUsername());
        return resp;
    }
}
