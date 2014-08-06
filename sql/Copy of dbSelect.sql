CONNECT 'jdbc:derby://localhost:1527/smt4DB;user=test;password=test';

SELECT o.id, u.login, u.name, SUM(c.price), s.name 
FROM orders o, users u, statuses s, carts c, products p 
WHERE o.status_id=s.id and o.user_id=u.id and c.order_id=o.id and p.id=c.product_id;

DISCONNECT;