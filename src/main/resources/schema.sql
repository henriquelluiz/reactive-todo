CREATE SCHEMA todos;

CREATE TYPE status AS ENUM ('TODO', 'DOING', 'DONE');

CREATE TABLE IF NOT EXISTS todos.task
(
    task_id     SERIAL PRIMARY KEY,
    title       VARCHAR(64)  NOT NULL,
    description VARCHAR(255) NOT NULL,
    status      STATUS       NOT NULL DEFAULT 'TODO',
    created_at  TIMESTAMP    NOT NULL DEFAULT current_timestamp,
    finished_at TIMESTAMP    NOT NULL DEFAULT current_timestamp
);