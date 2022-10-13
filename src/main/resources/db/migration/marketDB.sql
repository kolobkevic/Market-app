DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS customers_products;
CREATE TABLE products
(
    id    bigserial,
    title VARCHAR(255),
    price REAL,
    PRIMARY KEY (id)
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

CREATE TABLE customers
(
    id      bigserial,
    name    VARCHAR(255),
    surname VARCHAR(255),
    PRIMARY KEY (id)
);
INSERT INTO customers (name, surname)
VALUES ('Vladimir', 'Putin'),
       ('Joe', 'Biden'),
       ('Emmanuel', 'Macron'),
       ('Olaf', 'Scholz');

CREATE TABLE customers_products
(
    product_id  bigint,
    customer_id bigint,
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (customer_id) REFERENCES customers (id)
);
INSERT INTO customers_products (product_id, customer_id)
VALUES (1, 1),
       (3, 1),
       (8, 1),
       (4, 2),
       (18, 2),
       (29, 3),
       (24, 3),
       (30, 3),
       (31, 3),
       (16, 4);