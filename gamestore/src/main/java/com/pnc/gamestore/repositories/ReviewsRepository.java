package com.pnc.gamestore.repositories;

import com.pnc.gamestore.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, UUID> {
    List<Reviews> findByGameId(UUID gameId);
    boolean existsByGameIdAndUserId(UUID gameId, UUID userId);
}
