BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title varchar(255), price int);
INSERT INTO products (title, price) VALUES
('Product 1', 111),
('Product 2', 222),
('Product 3', 333),
('Product 4', 444),
('Product 5', 555),
('Product 6', 666),
('Product 7', 777),
('Product 8', 888),
('Product 9', 999);

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigserial PRIMARY KEY, name varchar(255));
INSERT INTO customers (name) VALUES
('Customer 1'),
('Customer 2'),
('Customer 3'),
('Customer 4'),
('Customer 5');

DROP TABLE IF EXISTS sales CASCADE;
CREATE TABLE sales (id bigserial PRIMARY KEY, customer_id bigint, product_id bigint, price int, FOREIGN KEY (customer_id) REFERENCES customers(id), FOREIGN KEY (product_id) REFERENCES products(id));
INSERT INTO sales (customer_id, product_id, price) VALUES
(1, 1, 111),
(2, 1, 222),
(2, 4, 225),
(3, 4, 333),
(3, 4, 333),
(5, 5, 555),
(5, 5, 557),
(4, 6, 444),
(1, 6, 111),
(4, 8, 444),
(3, 9, 338);

COMMIT;