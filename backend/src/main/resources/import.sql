INSERT INTO user (id, email, password, role) VALUES (1, 'admin@mps.ng', 'admin', 'admin');
INSERT INTO user (id, email, password, role) VALUES (2, 'demo@mps.ng', 'demo', 'customer');

INSERT INTO vendor (id, name) VALUES (1, 'DSTV');
INSERT INTO service (id, name, amount, vendor_id) VALUES (1, 'Compact', 6000, 1);