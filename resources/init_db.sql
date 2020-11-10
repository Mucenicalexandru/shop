DROP TABLE IF EXISTS registered_users;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_supplier;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS orders;


CREATE TABLE registered_users (
                       id      serial PRIMARY KEY NOT NULL,
                       first_name    VARCHAR(200) NOT NULL,
                       last_name     VARCHAR(200) NOT NULL,
                       country VARCHAR(200) NOT NULL,
                       address  VARCHAR(2000) NOT NULL,
                       postcode  VARCHAR(200) NOT NULL,
                       town  VARCHAR(200) NOT NULL,
                       phone VARCHAR(200) NOT NULL,
                       email VARCHAR(200) NOT NULL,
                       password VARCHAR(200) NOT NULL
);

CREATE TABLE products (
                      id serial PRIMARY KEY NOT NULL,
                      name VARCHAR(200) NOT NULL,
                      description VARCHAR(2000) NOT NULL,
                      default_price INTEGER NOT NULL,
                      default_currency VARCHAR(20) NOT NULL,
                      category_id INTEGER NOT NULL,
                      supplier_id INTEGER NOT NULL
);

CREATE TABLE cart (
                        id serial PRIMARY KEY NOT NULL,
                        product_id INTEGER NOT NULL,
                        quantity INTEGER NOT NULL,
                        total_price INTEGER NOT NULL,
                        user_id INTEGER NOT NULL
);

CREATE TABLE product_supplier(
                        id serial PRIMARY KEY NOT NULL,
                        name VARCHAR(200) NOT NULL,
                        description VARCHAR(2000) NOT NULL
);

CREATE TABLE product_category(
                         id serial PRIMARY KEY NOT NULL,
                         name VARCHAR(200) NOT NULL,
                         description VARCHAR(2000) NOT NULL,
                         department VARCHAR(2000) NOT NULL
);

CREATE TABLE orders(
                       id serial PRIMARY KEY NOT NULL,
                       date timestamp NOT NULL,
                       user_id INTEGER NOT NULL,
                       product_id INTEGER NOT NULL,
                       quantity INTEGER NOT NULL,
                       total_price INTEGER NOT NULL
);


ALTER TABLE ONLY products
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES product_category(id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES product_supplier(id);

ALTER TABLE ONLY cart
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products(id);

ALTER TABLE ONLY cart
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES registered_users(id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES registered_users(id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products(id);