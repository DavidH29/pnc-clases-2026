package com.pnc.gamestore.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Date created_at;

    @Column
    private String created_by;

    @Column
    private  Date updated_at;

    @Column
    private String updated_by;
}
