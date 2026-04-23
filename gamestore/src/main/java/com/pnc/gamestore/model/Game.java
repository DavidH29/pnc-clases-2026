package com.pnc.gamestore.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "video_games")
public class Game {
    @Id
    @Column
    public Integer id;
    @Column
    public String name;
    @Column
    public String genre;
    @Column
    public String classification;

    @Column(name = "game_developer")
    public String dev;

    @OneToOne
    @JoinColumn(name = "game_details_id")
    public GameDetails details;

    @OneToMany
    @JoinColumn(name = "review_id")
    public List<Reviews> reviews;


    public Game(){}


    public Game(Integer id, String name, String genre, String classification, String dev) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.classification = classification;
        this.dev = dev;
    }

}
