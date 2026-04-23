package com.pnc.gamestore.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Table(name = "platforms")
@Entity
public class Platforms {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String name;

    @Column
    private String company;

    @ManyToMany(mappedBy = "platforms")
    private List<Game> games;
}
