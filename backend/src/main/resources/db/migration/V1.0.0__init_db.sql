CREATE SEQUENCE  hibernate_sequence START 1;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR (255) UNIQUE NOT NULL,
    password VARCHAR (32) NOT NULL,
    role VARCHAR (32) NOT NULL
);

CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR (255) NOT NULL,
    phone VARCHAR (16) NOT NULL,
    user_id INT REFERENCES users (id)
);

CREATE TABLE vendors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

CREATE TABLE services (
    id SERIAL PRIMARY KEY,
    name VARCHAR(128),
    fixed BOOLEAN,
    amount INT,
    vendor_id INT REFERENCES vendors (id)
);

CREATE TABLE complaints (
    id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL REFERENCES customers (id),
    message TEXT NOT NULL,
    status VARCHAR(16),
    dateraised TIMESTAMP DEFAULT NOW(),
    dateresolved TIMESTAMP,
    resolvedby_id INT REFERENCES users (id),
    resolution TEXT
);

CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users (id),
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

INSERT INTO users (id, email, password, role) VALUES (1, 'admin@mps.ng', 'admin', 'admin');
INSERT INTO users (id, email, password, role) VALUES (2, 'demo@mps.ng', 'demo', 'customer');
INSERT INTO customers (name, phone, user_id) VALUES ('demo', '08030000000', 2);

INSERT INTO vendors (name) VALUES ('DSTV');
INSERT INTO services (name, amount, fixed, vendor_id) VALUES ('Compact', 6000, TRUE, 1);