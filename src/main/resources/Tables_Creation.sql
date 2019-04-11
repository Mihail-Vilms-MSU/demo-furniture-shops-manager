CREATE TABLE IF NOT EXISTS "product"
(
    id SERIAL PRIMARY KEY,
    name character varying(255) NOT NULL,
    price NUMERIC(12, 2) NOT NULL,
    description VARCHAR(4000),
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
/*
CREATE TABLE IF NOT EXISTS "order"
(
    id SERIAL PRIMARY KEY,
    price NUMERIC(12, 2) NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS "order_to_product" (
  id SERIAL PRIMARY KEY,
	product_id INT NOT NULL,
	order_id INT NOT NULL,
	amount INT NOT NULL
);
*/