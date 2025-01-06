CREATE TABLE teams
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    user_id INTEGER      NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    cave_id INTEGER      REFERENCES dragon_cave (id) ON DELETE SET NULL

);

CREATE TABLE team_characters
(
    team_id      INT NOT NULL,
    character_id INT NOT NULL,
    PRIMARY KEY (team_id, character_id),
    FOREIGN KEY (team_id) REFERENCES teams (id) ON DELETE CASCADE,
    FOREIGN KEY (character_id) REFERENCES person (id) ON DELETE CASCADE
);