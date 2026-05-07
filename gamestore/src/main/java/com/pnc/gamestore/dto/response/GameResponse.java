package com.pnc.gamestore.dto.response;

import com.pnc.gamestore.model.enums.Classification;
import com.pnc.gamestore.model.enums.Genre;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class GameResponse {

    private UUID id;
    private String name;
    private Classification classification;
    private List<Genre> genres;
    private String dev;
    private GameDetailsResponse details;
    private List<String> platforms;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    public GameResponse() {}

    // Nested details DTO
    public static class GameDetailsResponse {
        private String about;
        private Integer publishYear;

        public String getAbout() { return about; }
        public void setAbout(String about) { this.about = about; }
        public Integer getPublishYear() { return publishYear; }
        public void setPublishYear(Integer publishYear) { this.publishYear = publishYear; }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Classification getClassification() { return classification; }
    public void setClassification(Classification classification) { this.classification = classification; }

    public List<Genre> getGenres() { return genres; }
    public void setGenres(List<Genre> genres) { this.genres = genres; }

    public String getDev() { return dev; }
    public void setDev(String dev) { this.dev = dev; }

    public GameDetailsResponse getDetails() { return details; }
    public void setDetails(GameDetailsResponse details) { this.details = details; }

    public List<String> getPlatforms() { return platforms; }
    public void setPlatforms(List<String> platforms) { this.platforms = platforms; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }
}
