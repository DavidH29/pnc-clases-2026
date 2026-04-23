package com.pnc.gamestore.model;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "reviews")
@Entity
public class Reviews {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

}
