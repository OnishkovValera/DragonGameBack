CREATE TYPE admin_request_status AS ENUM ('PENDING', 'APPROVED', 'REJECTED');
CREATE CAST (character varying AS admin_request_status) with inout as assignment;

CREATE TABLE admin_requests
(
    id             SERIAL PRIMARY KEY,
    user_id        BIGINT REFERENCES users (id) ON DELETE CASCADE,
    status         admin_request_status NOT NULL ,
    request_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed_by   BIGINT REFERENCES users (id),
    processed_date TIMESTAMP,
    comment        TEXT
);

