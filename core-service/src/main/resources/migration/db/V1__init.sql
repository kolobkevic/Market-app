DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS user_roles CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS order_items CASCADE;

CREATE TABLE products
(
    id         BIGSERIAL PRIMARY KEY,
    title      VARCHAR(255),
    price      REAL NOT NULL,
    created_at TIMESTAMP DEFAULT current_timestamp,
    updated_at TIMESTAMP DEFAULT current_timestamp
);
INSERT INTO products (title, price)
VALUES ('Молоко', 98.5),
       ('Чай', 145.8),
       ('Печенье', 189.1),
       ('Сок', 124.46),
       ('Хлеб', 30.0),
       ('Кофе', 355.7),
       ('Тунец', 450.1),
       ('Помидоры', 50.4),
       ('Ананасы', 224.8),
       ('Виски', 2950.0),
       ('Сыр', 451.9),
       ('Чипсы', 125.5),
       ('Картошка', 45.0),
       ('Майонез', 89.8),
       ('Кетчуп', 45.46),
       ('Мороженое', 50.1),
       ('Говядина', 550.2),
       ('Курица', 450.1),
       ('Свинина', 390.4),
       ('Яблоки', 110.8),
       ('Перец', 15.2),
       ('Сметана', 115.9),
       ('Каша', 97.5),
       ('Салфетки', 45.4),
       ('Виноград', 97.99),
       ('Влажные салфетки', 39.0),
       ('Морковь', 36.6),
       ('Кефир', 79.7),
       ('Шпроты', 150.15),
       ('Укроп', 30.0),
       ('Пакет', 3.0),
       ('Водка', 749.99),
       ('Лимон', 40.0);

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

CREATE TABLE orders
(
    id           BIGSERIAL PRIMARY KEY,
    user_id      BIGINT NOT NULL REFERENCES users (id),
    total_price  REAL    NOT NULL,
    address      VARCHAR(255),
    phone_number VARCHAR(50),
    created_at   TIMESTAMP DEFAULT current_timestamp,
    updated_at   TIMESTAMP DEFAULT current_timestamp
);

INSERT INTO orders(user_id, total_price, address, phone_number)
VALUES (1, 394, 'adress1', '12345');

CREATE TABLE order_items
(
    id                BIGSERIAL PRIMARY KEY,
    product_id        BIGINT NOT NULL REFERENCES products (id),
    order_id          BIGINT NOT NULL REFERENCES orders (id),
    quantity          INT    NOT NULL,
    price_per_product REAL    NOT NULL,
    price             REAL    NOT NULL,
    created_at        TIMESTAMP DEFAULT current_timestamp,
    updated_at        TIMESTAMP DEFAULT current_timestamp
);

INSERT INTO order_items(product_id, order_id, quantity, price_per_product, price)
VALUES (1, 1, 4, 98.5, 394);