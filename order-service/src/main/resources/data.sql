INSERT INTO orders (total_price, order_status) VALUES (150.50, 'CONFIRMED');
INSERT INTO orders (total_price, order_status) VALUES (45.00, 'PENDING');
INSERT INTO orders (total_price, order_status) VALUES (2100.99, 'DELIVERED');
INSERT INTO orders (total_price, order_status) VALUES (0.00, 'CANCELLED');
INSERT INTO orders (total_price, order_status) VALUES (75.25, 'CONFIRMED');


-- Items for Order #1 (Confirmed)
INSERT INTO order_item (quantity, order_id, product_id) VALUES (2, 1, 101);
INSERT INTO order_item (quantity, order_id, product_id) VALUES (1, 1, 102);

-- Items for Order #2 (Pending)
INSERT INTO order_item (quantity, order_id, product_id) VALUES (5, 2, 201);

-- Items for Order #3 (Delivered)
INSERT INTO order_item (quantity, order_id, product_id) VALUES (1, 3, 305);
INSERT INTO order_item (quantity, order_id, product_id) VALUES (10, 3, 306);