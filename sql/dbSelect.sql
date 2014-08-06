CONNECT 'jdbc:derby://localhost:1527/smt4DB;user=test;password=test';

SELECT * FROM users;

SELECT * FROM carts;
SELECT * FROM products;
SELECT * FROM orders;
SELECT * FROM categories;
SELECT * FROM statuses;
SELECT * FROM users;
SELECT * FROM roles;

DISCONNECT;