CREATE TABLE upload_history(
    id SERIAL PRIMARY KEY,
    creation_date timestamptz,
    is_success BOOLEAN,
    user_id INTEGER REFERENCES users (id),
    object_url VARCHAR
)