package com.pnc.gamestore.model;

import com.pnc.gamestore.model.enums.Classification;
import com.pnc.gamestore.model.enums.Genre;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "video_games")
public class Game {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Classification classification;

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "game_genres", joinColumns = @JoinColumn(name = "game_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private List<Genre> genres = new ArrayList<>();

    @Column(name = "game_developer", nullable = false)
    private String dev;

    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private GameDetails details;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reviews> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "game_platforms",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private List<Platforms> platforms = new ArrayList<>();

    // Audit fields
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false, updatable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    public Game() {}

    public Game(String name, Classification classification, List<Genre> genres, String dev) {
        this.name = name;
        this.classification = classification;
        this.genres = genres;
        this.dev = dev;
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

    public GameDetails getDetails() { return details; }
    public void setDetails(GameDetails details) { this.details = details; }

    public List<Reviews> getReviews() { return reviews; }
    public void setReviews(List<Reviews> reviews) { this.reviews = reviews; }

    public List<Platforms> getPlatforms() { return platforms; }
    public void setPlatforms(List<Platforms> platforms) { this.platforms = platforms; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public User getCreatedBy() { return createdBy; }
    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }

    public User getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(User updatedBy) { this.updatedBy = updatedBy; }
}