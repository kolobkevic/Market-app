DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS user_roles CASCADE;

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(30) NOT NULL UNIQUE,
    password   VARCHAR(80) NOT NULL,
    email      VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT current_timestamp,
    updated_at TIMESTAMP DEFAULT current_timestamp
);
INSERT INTO users (username, password, email)
VALUES ('vladimir', '$2y$10$VM49AKABGnZR4BW5BbKPN.fkhoj79bKYmEV2BoMwG3E3SgIKwPtZC', 'putin@mail.ru'),
       ('joe', '$2y$10$TmMSg69/d2fWQ7x91l8ZvOcLP5NW2.VCE1iteij/KMVGu2Z9.HWIO', 'biden@mail.us'),
       ('emmanuel', '$2y$10$.XegeGgv6kO6gduizYG/BOvADGImYxc30HT6QdpCxNxWhClkP3czO', 'macron@mail.fr'),
       ('olaf', '$2y$10$3qve0urEbVCAAqqRdqBw0OjjrlwMHJ7kmipmcef7qBYJWkZBI3kEm', 'scholz@mail.de');

CREATE TABLE roles
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT current_timestamp,
    updated_at TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE user_roles
(
    user_id    BIGINT NOT NULL REFERENCES users (id),
    role_id    INT    NOT NULL REFERENCES roles (id),
    created_at TIMESTAMP DEFAULT current_timestamp,
    updated_at TIMESTAMP DEFAULT current_timestamp,
    PRIMARY KEY (user_id, role_id)
);
INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN');

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 3),
       (1, 2),
       (1, 1),
       (2, 1),
       (2, 2),
       (3, 1),
       (4, 1);