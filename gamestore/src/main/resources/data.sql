INSERT INTO platforms (id, name, company)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Nintendo Switch', 'Nintendo'),
       ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'PlayStation 5', 'Sony'),
       ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'PC', 'Various'),
       ('d0eebc99-9c0b-4ef8-bb6d-6bb9bd380a17', 'Xbox Series X', 'Microsoft'),
       ('e0eebc99-9c0b-4ef8-bb6d-6bb9bd380a18', 'Mobile', 'Various');

INSERT INTO video_games (id, name, classification, game_developer)
VALUES ('a97869ad-eac3-4d32-9dcc-bb4d146e7f33', 'The Legend of Zelda: Breath of the Wild', 'E10', 'Nintendo'),
       ('0b83f29a-5e25-485c-816d-eca0a5cc1d78', 'God of War', 'M', 'Santa Monica Studio'),
       ('39aa703a-6184-455d-9d0e-296e646c8a89', 'Minecraft', 'E10', 'Mojang'),
       ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 'Forza Horizon 5', 'E', 'Playground Games'),
       ('8c4e31e3-12a4-4b9c-8c6e-9b3a8de7b2f1', 'Resident Evil 4 Remake', 'M', 'Capcom'),
       ('5f7d2a9e-b3c4-4d6f-b8a3-6c1e9a0d5f7b', 'Hollow Knight', 'E10', 'Team Cherry'),
       ('a1b2c3d4-e5f6-7890-1234-567890abcdef', 'Stardew Valley', 'E', 'ConcernedApe'),
       ('b2c3d4e5-f6a7-8901-2345-67890abcdef1', 'Call of Duty: Modern Warfare III', 'M', 'Infinity Ward'),
       ('c3d4e5f6-a7b8-9012-3456-7890abcdef12', 'Celeste', 'E', 'Maddy Makes Games');


INSERT INTO game_genres (game_id, genre)
VALUES ('a97869ad-eac3-4d32-9dcc-bb4d146e7f33', 'ADVENTURE'),
       ('a97869ad-eac3-4d32-9dcc-bb4d146e7f33', 'PUZZLE'),

       ('0b83f29a-5e25-485c-816d-eca0a5cc1d78', 'ADVENTURE'),

       ('39aa703a-6184-455d-9d0e-296e646c8a89', 'ADVENTURE'),
       ('39aa703a-6184-455d-9d0e-296e646c8a89', 'PUZZLE'),

       ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 'RACING'),

       ('8c4e31e3-12a4-4b9c-8c6e-9b3a8de7b2f1', 'SHOOTER'),

       ('5f7d2a9e-b3c4-4d6f-b8a3-6c1e9a0d5f7b', 'ADVENTURE'),

       ('a1b2c3d4-e5f6-7890-1234-567890abcdef', 'ADVENTURE'),
       ('a1b2c3d4-e5f6-7890-1234-567890abcdef', 'PUZZLE'),

       ('b2c3d4e5-f6a7-8901-2345-67890abcdef1', 'SHOOTER'),

       ('c3d4e5f6-a7b8-9012-3456-7890abcdef12', 'ADVENTURE'),
       ('c3d4e5f6-a7b8-9012-3456-7890abcdef12', 'PUZZLE');

INSERT INTO game_platforms (game_id, platform_id)
VALUES ('a97869ad-eac3-4d32-9dcc-bb4d146e7f33', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),

       ('0b83f29a-5e25-485c-816d-eca0a5cc1d78', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12'),
       ('0b83f29a-5e25-485c-816d-eca0a5cc1d78', 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13'),

       ('39aa703a-6184-455d-9d0e-296e646c8a89', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
       ('39aa703a-6184-455d-9d0e-296e646c8a89', 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13'),
       ('39aa703a-6184-455d-9d0e-296e646c8a89', 'e0eebc99-9c0b-4ef8-bb6d-6bb9bd380a18'),

       ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a17'),
       ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13'),

       ('8c4e31e3-12a4-4b9c-8c6e-9b3a8de7b2f1', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12'),
       ('8c4e31e3-12a4-4b9c-8c6e-9b3a8de7b2f1', 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a17'),
       ('8c4e31e3-12a4-4b9c-8c6e-9b3a8de7b2f1', 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13'),

       ('5f7d2a9e-b3c4-4d6f-b8a3-6c1e9a0d5f7b', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
       ('5f7d2a9e-b3c4-4d6f-b8a3-6c1e9a0d5f7b', 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13'),

       ('a1b2c3d4-e5f6-7890-1234-567890abcdef', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
       ('a1b2c3d4-e5f6-7890-1234-567890abcdef', 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13'),
       ('a1b2c3d4-e5f6-7890-1234-567890abcdef', 'e0eebc99-9c0b-4ef8-bb6d-6bb9bd380a18'),

       ('b2c3d4e5-f6a7-8901-2345-67890abcdef1', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12'),
       ('b2c3d4e5-f6a7-8901-2345-67890abcdef1', 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a17'),
       ('b2c3d4e5-f6a7-8901-2345-67890abcdef1', 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13'),

       ('c3d4e5f6-a7b8-9012-3456-7890abcdef12', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
       ('c3d4e5f6-a7b8-9012-3456-7890abcdef12', 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13');


INSERT INTO reviews (id, player, rating, comment, game_id)
VALUES ('d0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'GamerPro', 5, 'Obra maestra!', 'a97869ad-eac3-4d32-9dcc-bb4d146e7f33'),
       ('e0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15', 'KratosFan', 5, 'Espectacular', '0b83f29a-5e25-485c-816d-eca0a5cc1d78'),
       ('f0eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'BuilderFan', 5, 'Creatividad infinita',
        '39aa703a-6184-455d-9d0e-296e646c8a89'),
       ('f1eebc99-9c0b-4ef8-bb6d-6bb9bd380a17', 'SpeedRacer', 5, 'El mejor juego de carreras',
        'f47ac10b-58cc-4372-a567-0e02b2c3d479'),
       ('f2eebc99-9c0b-4ef8-bb6d-6bb9bd380a18', 'Survivor', 5, 'Terror de calidad',
        '8c4e31e3-12a4-4b9c-8c6e-9b3a8de7b2f1'),
       ('f3eebc99-9c0b-4ef8-bb6d-6bb9bd380a19', 'IndieFan', 5, 'Arte y jugabilidad',
        '5f7d2a9e-b3c4-4d6f-b8a3-6c1e9a0d5f7b'),
       ('f4eebc99-9c0b-4ef8-bb6d-6bb9bd380a20', 'FarmLife', 5, 'Relajante y divertido',
        'a1b2c3d4-e5f6-7890-1234-567890abcdef'),
       ('f5eebc99-9c0b-4ef8-bb6d-6bb9bd380a21', 'FPSPro', 4, 'Multijugador intenso',
        'b2c3d4e5-f6a7-8901-2345-67890abcdef1'),
       ('f6eebc99-9c0b-4ef8-bb6d-6bb9bd380a22', 'PlatformerKing', 5, 'Historia conmovedora',
        'c3d4e5f6-a7b8-9012-3456-7890abcdef12');