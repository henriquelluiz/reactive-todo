CREATE SCHEMA todos;

CREATE TABLE IF NOT EXISTS todos.task
(
    task_id     SERIAL PRIMARY KEY,
    title       VARCHAR(64)  NOT NULL,
    description VARCHAR(255),
    status      VARCHAR(5) NOT NULL DEFAULT 'TODO',
    created_at  TIMESTAMP    NOT NULL DEFAULT current_timestamp,
    finished_at TIMESTAMP    NOT NULL DEFAULT current_timestamp
);