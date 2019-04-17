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
	amount INT NOT NULL
);