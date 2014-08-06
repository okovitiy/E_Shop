-- ST4Example DB creation script for Apache Derby

CONNECT 'jdbc:derby://localhost:1527/smt4DB;create=true;user=test;password=test';

DROP TABLE carts;
DROP TABLE products;
DROP TABLE orders;
DROP TABLE categories;
DROP TABLE statuses;
DROP TABLE users;
DROP TABLE roles;
------------------------------------------------------------------------------
CREATE TABLE roles(
	id   	    INTEGER 	NOT NULL PRIMARY KEY,
	name 	    VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'client');
INSERT INTO roles VALUES(2, 'blockedClient');
------------------------------------------------------------------------------
CREATE TABLE users(
	id          INT 		NOT NULL generated always AS identity PRIMARY KEY,
	name 	    VARCHAR(20) NOT NULL,
	login 	    VARCHAR(20) NOT NULL UNIQUE,
	password    VARCHAR(20) NOT NULL,
	email 	    VARCHAR(20) NOT NULL,
	locale_name VARCHAR(20),
	role_id     INTEGER     NOT NULL REFERENCES roles(id) 
		ON DELETE CASCADE 
		ON UPDATE RESTRICT
);
------------------------------------------------------------------------------
CREATE TABLE statuses(
	id 		    INTEGER 	NOT NULL PRIMARY KEY,
	name 	    VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO statuses VALUES(0, 'opened');
INSERT INTO statuses VALUES(1, 'registered');
INSERT INTO statuses VALUES(2, 'paid');
INSERT INTO statuses VALUES(3, 'closed');
INSERT INTO statuses VALUES(4, 'canceled');
------------------------------------------------------------------------------
CREATE TABLE categories(
	id 		    INTEGER 	NOT NULL PRIMARY KEY,
	name 	    VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO categories VALUES(1, 'TVs');
INSERT INTO categories VALUES(2, 'Laptops');
INSERT INTO categories VALUES(3, 'Phones');
------------------------------------------------------------------------------
CREATE TABLE orders(
	id 		    INTEGER 	NOT NULL generated always AS identity PRIMARY KEY,
	user_id     INTEGER 	NOT NULL REFERENCES users(id),
	status_id   INTEGER 	NOT NULL REFERENCES statuses(id)
);
------------------------------------------------------------------------------
CREATE TABLE products(
	id 		    INTEGER 	NOT NULL generated always AS identity PRIMARY KEY,
	name 	    VARCHAR(30) NOT NULL,
	price 	    INTEGER 	NOT NULL,
	color 	    VARCHAR(10) NOT NULL,
	size 	    VARCHAR(30) NOT NULL,
	novelty     DATE        NOT NULL,
	quantity    INTEGER 	NOT NULL,
	category_id INTEGER 	NOT NULL REFERENCES categories(id)
);
------------------------------------------------------------------------------
CREATE TABLE carts(
	order_id    INTEGER 	NOT NULL REFERENCES orders(id),
	product_id  INTEGER 	NOT NULL REFERENCES products(id),
	quantity    INTEGER 	NOT NULL,
	price 	    INTEGER 	NOT NULL
);

------------------------------------------------------------------------------
INSERT INTO users VALUES(DEFAULT, 'Andrew', 'admin', 'admin', 'admin@gmail.com', NULL, 0);
INSERT INTO users VALUES(DEFAULT, 'Ivan', 'user', 'user', 'user@gmail.com', NULL, 1);
INSERT INTO users VALUES(DEFAULT, 'Vasya', 'user2', 'user2', 'user2@gmail.com', NULL, 1);

INSERT INTO orders VALUES(DEFAULT, 2, 1);
INSERT INTO orders VALUES(DEFAULT, 3, 2);
INSERT INTO orders VALUES(DEFAULT, 2, 3);
INSERT INTO orders VALUES(DEFAULT, 3, 4);


INSERT INTO products VALUES(DEFAULT, 'Samsung UE-32F5000', 4118.00, 'black', '738x505x265', '2014-06-13', 5, 1);
INSERT INTO products VALUES(DEFAULT, 'LG 42LA620V', 8075.00, 'grey', '964x631x294', '2014-06-14', 3, 1);
INSERT INTO products VALUES(DEFAULT, 'UE-32F5300', 4596.00, 'white', '738x505x192', '2014-06-15', 1, 1);

INSERT INTO products VALUES(DEFAULT, 'Lenovo IdeaPad B590G', 4474.00, 'blue', '378x252x33', '2014-06-16', 4, 2);
INSERT INTO products VALUES(DEFAULT, 'ASUS X551CA', 3804.00, 'black', '380x251x37', '2014-06-17', 1, 2);
INSERT INTO products VALUES(DEFAULT, 'Acer P253-MG-33114G50Mnks', 5146.00, 'green', '382x253x33', '2014-06-18', 3, 2);

INSERT INTO products VALUES(DEFAULT, 'Lenovo S820', 2375.00, 'white', '70x140x9', '2014-06-19', 2, 3);
INSERT INTO products VALUES(DEFAULT, 'Samsung GT-i9500 Galaxy S4', 5286.00, 'red', '70x137x8', '2014-06-20', 1, 3);
INSERT INTO products VALUES(DEFAULT, 'LG L90 Dual D410', 2819.00, 'orange', '66x132x10', '2014-06-21', 6, 3);

INSERT INTO carts VALUES(1, 1, 1, 4118.00);
INSERT INTO carts VALUES(1, 5, 1, 3804.00);
INSERT INTO carts VALUES(1, 2, 1, 8075.00);

INSERT INTO carts VALUES(2, 1, 1, 4118.00);
INSERT INTO carts VALUES(2, 3, 1, 4596.00);
INSERT INTO carts VALUES(2, 6, 1, 5146.00);

INSERT INTO carts VALUES(3, 4, 1, 4474.00);

INSERT INTO carts VALUES(4, 6, 1, 5146.00);

DISCONNECT;