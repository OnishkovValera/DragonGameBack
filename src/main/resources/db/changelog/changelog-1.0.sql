-- Создание таблицы пользователей
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       login VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(10) CHECK (role IN ('ADMIN', 'USER')) NOT NULL
);

-- Создание таблицы для перечислений
CREATE TYPE dragon_character AS ENUM ('CUNNING', 'GOOD', 'CHAOTIC_EVIL', 'FICKLE');
CREATE TYPE color AS ENUM ('BLACK', 'BLUE', 'ORANGE', 'BROWN');
CREATE TYPE country AS ENUM ('FRANCE', 'SPAIN', 'VATICAN', 'JAPAN');

-- Создание таблиц связанных объектов
CREATE TABLE coordinates (
                             id SERIAL PRIMARY KEY,
                             x BIGINT NOT NULL CHECK (x <= 98),
                             y BIGINT NOT NULL CHECK (y > -462)
);

CREATE TABLE dragon_cave (
                             id SERIAL PRIMARY KEY,
                             depth DOUBLE PRECISION NOT NULL,
                             number_of_treasures DOUBLE PRECISION CHECK (number_of_treasures > 0)
);

CREATE TABLE location (
                          id SERIAL PRIMARY KEY,
                          x DOUBLE PRECISION NOT NULL,
                          y INT NOT NULL,
                          z BIGINT NOT NULL
);

CREATE TABLE person (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        eye_color color,
                        hair_color color,
                        location_id INT REFERENCES location(id) ON DELETE SET NULL,
                        weight BIGINT NOT NULL CHECK (weight > 0),
                        nationality country,
                        created_by INT REFERENCES users(id) ON DELETE SET NULL,
                        created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
                        updated_by INT REFERENCES users(id) ON DELETE SET NULL,
                        updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE dragon_head (
                             id SERIAL PRIMARY KEY,
                             tooth_count DOUBLE PRECISION
);

-- Создание таблицы драконов
CREATE TABLE dragon (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL CHECK (name <> ''),
                        coordinates_id INT NOT NULL REFERENCES coordinates(id) ON DELETE CASCADE,
                        creation_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
                        cave_id INT REFERENCES dragon_cave(id) ON DELETE SET NULL,
                        killer_id INT REFERENCES person(id) ON DELETE SET NULL,
                        age INT NOT NULL CHECK (age > 0),
                        description TEXT,
                        speaking BOOLEAN NOT NULL,
                        character dragon_character NOT NULL,
                        head_id INT REFERENCES dragon_head(id) ON DELETE SET NULL,
                        created_by INT REFERENCES users(id) ON DELETE SET NULL,
                        created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
                        updated_by INT REFERENCES users(id) ON DELETE SET NULL,
                        updated_at TIMESTAMP WITH TIME ZONE
);

-- Создание триггера для обновления временной метки
CREATE OR REPLACE FUNCTION update_timestamp()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_update_timestamp
    BEFORE UPDATE ON dragon
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER set_update_timestamp
    BEFORE UPDATE ON person
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();
