package com.pnc.gamestore.repositories;

import com.pnc.gamestore.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewsRepository extends JpaRepository<Reviews, UUID> {
    List<Reviews> findByGameId(Integer gameId);
    List<Reviews> findByGameId(UUID gameId);
    boolean existsByGameIdAndUser(UUID gameId, String user);
}

