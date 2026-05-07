package com.pnc.gamestore.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class GameDetailsRequest {

    private String about;

    @Min(value = 1975, message = "Publish year must be 1975 or later")
    @Max(value = 2026, message = "Publish year is not valid")
    private Integer publishYear;

    public GameDetailsRequest() {}

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }

    public Integer getPublishYear() { return publishYear; }
    public void setPublishYear(Integer publishYear) { this.publishYear = publishYear; }
}
