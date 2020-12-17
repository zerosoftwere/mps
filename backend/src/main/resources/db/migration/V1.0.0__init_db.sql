CREATE SEQUENCE  hibernate_sequence START 1;

CREATE TABLE user (
    id SERIAL PRIMARY KEY,
    email VARCHAR (255) UNIQUE NOT NULL,
    password VARCHAR (32) NOT NULL,
    role VARCHAR (32) NOT NULL
);

CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR (255) NOT NULL,
    phone VARCHAR (16) NOT NULL,
    user_id INT REFERENCES user (id)
);

CREATE TABLE vendor (
    id SERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

CREATE TABLE service (
    id SERIAL PRIMARY KEY,
    name VARCHAR(128),
    fixed BOOLEAN,
    amount INT,
    vendor_id INT REFERENCES vendor (id)
);

CREATE TABLE complaint (
    id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL REFERENCES customer (id),
    message TINYTEXT NOT NULL,
    status VARCHAR(16),
    dateraised TIMESTAMP DEFAULT NOW(),
    dateresolved TIMESTAMP,
    resolvedby_id INT REFERENCES user (id),
    resolution TINYTEXT
);

CREATE TABLE transaction (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES user (id),
    vendor VARCHAR(128) NOT NULL,
    service VARCHAR(128) NOT NULL,
    amount NUMERIC NOT NULL,
    status VARCHAR(16) NOT NULL,
    email VARCHAR (255) NOT NULL,
    phone VARCHAR (16) NOT NULL,
    customer VARCHAR (255) NOT NULL,
    datecreated TIMESTAMP DEFAULT NOW(),
    dateupdated TIMESTAMP
);

INSERT INTO user (id, email, password, role) VALUES (1, 'admin@mps.ng', 'admin', 'admin');
INSERT INTO user (id, email, password, role) VALUES (2, 'demo@mps.ng', 'demo', 'customer');
INSERT INTO customer (name, phone, user_id) VALUES ('demo', '08030000000', 2);

INSERT INTO vendor (name) VALUES ('DSTV');
INSERT INTO service (name, amount, fixed, vendor_id) VALUES ('Compact', 6000, TRUE, 1);