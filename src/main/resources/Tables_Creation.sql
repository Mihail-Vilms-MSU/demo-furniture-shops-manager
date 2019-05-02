CREATE TABLE IF NOT EXISTS "product"
(
    id SERIAL PRIMARY KEY,
    name character varying(255) NOT NULL,
    price NUMERIC(12, 2) NOT NULL,
    "type" character varying(255) NOT NULL,
    description VARCHAR(4000),
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS "shop"
(
    id SERIAL PRIMARY KEY,
    name character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    state character varying(255) NOT NULL,
    address character varying(255) NOT NULL,
    phone character varying(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS "shop_to_product" (
  id SERIAL PRIMARY KEY,
	shop_id INT NOT NULL,
	product_id INT NOT NULL,
	amount INT NOT NULL,

	FOREIGN KEY (shop_id) REFERENCES shop (id),
	FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE IF NOT EXISTS "employee"
(
    id SERIAL PRIMARY KEY,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    phone character varying(255) NOT NULL,
    email character varying(255) NOT NULL,

    shop_id INT NOT NULL,
    role character varying(255) NOT NULL,

    FOREIGN KEY (shop_id) REFERENCES shop (id)
);

CREATE TABLE IF NOT EXISTS "purchase"
(
    id SERIAL PRIMARY KEY,
    shop_id INT,
    employee_id INT NOT NULL,
    total_price NUMERIC(12, 2),

    registered_at TIMESTAMP,

    FOREIGN KEY (shop_id) REFERENCES shop (id),
	  FOREIGN KEY (employee_id) REFERENCES employee (id)
);

CREATE TABLE IF NOT EXISTS "products_to_purchase"
(
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL,
    purchase_id INT NOT NULL,
	  amount INT NOT NULL,

	  FOREIGN KEY (product_id) REFERENCES product (id),
	  FOREIGN KEY (purchase_id) REFERENCES purchase (id)
);

CREATE VIEW IF NOT EXISTS amount_of_products AS
	SELECT pr.name as "Product Name", s.name as "Shop Name", s.city as "City", s.state as "State", sp.amount as "Amount of Product" FROM product pr
	LEFT JOIN shop_to_product sp ON pr.id = sp.product_id
	JOIN shop s ON s.id=sp.shop_id;