package com.pnc.gamestore.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ValueGenerationType;

import java.util.UUID;

@Table(name = "game_details")
@Entity
public class GameDetails {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String about;

    @Column()
    private Integer publishYear;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;
}
