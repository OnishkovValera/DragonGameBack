-- Заполнение таблицы users
INSERT INTO users (id, name, login, password, role)
VALUES ('1', 'Admin User', 'admin', '$2a$10$36nYuNbgRJaFC5O3FV/aNekUFRz9Xuv/QE8/RLUA64zWZYL.aoTBi', 'ADMIN'),
       ('2', 'Regular User', 'user1', '$2a$10$2De9Fgm61gyQ1ES.zITdyuOX/X63Q1H2JCMtRcbXmz.wrRJNQgMVC', 'USER'),
       ('3', 'Valera', 'login', '$2a$10$2De9Fgm61gyQ1ES.zITdyuOX/X63Q1H2JCMtRcbXmz.wrRJNQgMVC', 'USER');
-- Заполнение таблицы coordinates
INSERT INTO coordinates (id, x, y)
VALUES (1, 10, 0),
       (2, 50, -100),
       (3, 98, -460);

-- Заполнение таблицы dragon_cave
INSERT INTO dragon_cave (id, depth, number_of_treasures)
VALUES (1, 500.0, 100.0),
       (2, 1000.0, 200.0),
       (3, 300.0, NULL);

-- Заполнение таблицы location
INSERT INTO location (id, x, y, z)
VALUES (1, 10.0, 20, 30),
       (2, 40.0, 50, 60),
       (3, 70.0, 80, 90);

-- Заполнение таблицы person
INSERT INTO person (id, name, eye_color, hair_color,
                    location_id, weight, nationality,
                    created_by)
VALUES (1, 'John the Brave', 'BLUE', 'BLACK', 1, 80, 'FRANCE', 1),
       (2, 'Alice the Swift', 'BROWN', 'ORANGE', 2, 60, 'SPAIN', 1),
       (3, 'Killer Joe', NULL, NULL, 3, 90, NULL, 3);

-- Заполнение таблицы dragon_head
INSERT INTO dragon_head (id, tooth_count)
VALUES (1, 50.0),
       (2, 100.0),
       (3, NULL);

-- Заполнение таблицы dragon
INSERT INTO dragon (name, coordinates_id, cave_id,
                    killer_id, age, description,
                    speaking, character, head_id,
                    created_by)

VALUES ('Fire Dragon', 1, 1, 1, 300, 'A fiery beast', true, 'CUNNING', 1, 3),
       ('Water Dragon', 2, 2, 2, 150, 'Lives near the ocean', false, 'GOOD', 2, 1),
       ('Earth Dragon', 3, NULL, NULL, 400, NULL, true, 'CHAOTIC_EVIL', 3, 1);
