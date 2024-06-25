CREATE TABLE IF NOT EXISTS "user"
(
    id             uuid PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    age            INTEGER      NOT NULL,
    marital_status VARCHAR(40)  NOT NULL,
    created_at     TIMESTAMP    NOT NULL
);

CREATE INDEX IF NOT EXISTS user_id_index ON "user" (id);
