DROP TABLE IF EXISTS products CASCADE;
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

CREATE TABLE orders
(
    id           BIGSERIAL PRIMARY KEY,
    username     VARCHAR(255) NOT NULL,
    total_price  REAL    NOT NULL,
    address      VARCHAR(255),
    phone_number VARCHAR(50),
    created_at   TIMESTAMP DEFAULT current_timestamp,
    updated_at   TIMESTAMP DEFAULT current_timestamp
);

INSERT INTO orders(username, total_price, address, phone_number)
VALUES ('vladimir', 394, 'adress1', '12345');

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