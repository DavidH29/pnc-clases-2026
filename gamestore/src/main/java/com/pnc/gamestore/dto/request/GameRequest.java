package com.pnc.gamestore.dto.request;

import com.pnc.gamestore.model.enums.Classification;
import com.pnc.gamestore.model.enums.Genre;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class GameRequest {

    @NotBlank(message = "Game name is required")
    private String name;

    @NotNull(message = "Classification is required")
    private Classification classification;

    private List<Genre> genres;

    @NotBlank(message = "Developer is required")
    private String dev;

    @NotEmpty(message = "At least one platform is required")
    private List<UUID> platformIds;

    @Valid
    private GameDetailsRequest details;

    public GameRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Classification getClassification() { return classification; }
    public void setClassification(Classification classification) { this.classification = classification; }

    public List<Genre> getGenres() { return genres; }
    public void setGenres(List<Genre> genres) { this.genres = genres; }

    public String getDev() { return dev; }
    public void setDev(String dev) { this.dev = dev; }

    public List<UUID> getPlatformIds() { return platformIds; }
    public void setPlatformIds(List<UUID> platformIds) { this.platformIds = platformIds; }

    public GameDetailsRequest getDetails() { return details; }
    public void setDetails(GameDetailsRequest details) { this.details = details; }
}
