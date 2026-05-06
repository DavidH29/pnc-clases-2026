package com.pnc.gamestore.services;

import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.model.Reviews;
import com.pnc.gamestore.repositories.ReviewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewsRepository reviewsRepository;
    private final GameService gameService;

    public ReviewService(ReviewsRepository reviewsRepository, GameService gameService) {
        this.reviewsRepository = reviewsRepository;
        this.gameService = gameService;
    }

    public List<Reviews> getReviewsByGame(UUID gameId) {
        return reviewsRepository.findByGameId(gameId);
    }

    public Reviews createReview(UUID gameId, Reviews review) {
        Game game = gameService.getById(gameId);
        if (game == null) return null;

        if (review.getUser() == null || review.getUser().isBlank() ||
                review.getRating() == null ||
                review.getComment() == null || review.getComment().isBlank()) {
            return null;
        }

        if (reviewsRepository.existsByGameIdAndUser(gameId, review.getUser())) {
            return null;
        }

        review.setGame(game);
        return reviewsRepository.save(review);
    }


    public Reviews updateReview(UUID reviewId, Reviews reviewData) {
        Optional<Reviews> opt = reviewsRepository.findById(reviewId);
        if (opt.isEmpty()) return null;

        Reviews existing = opt.get();
        if (reviewData.getRating() == null ||
                reviewData.getComment() == null || reviewData.getComment().isBlank()) {
            return null;
        }

        existing.setRating(reviewData.getRating());
        existing.setComment(reviewData.getComment());
        return reviewsRepository.save(existing);
    }

    public Reviews deleteReview(UUID reviewId) {
        Optional<Reviews> opt = reviewsRepository.findById(reviewId);
        if (opt.isPresent()) {
            reviewsRepository.delete(opt.get());
            return opt.get();
        }
        return null;
    }
}
