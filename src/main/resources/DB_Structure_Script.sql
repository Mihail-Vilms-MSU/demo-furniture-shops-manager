  /*Tables creation >>>>>>>>>>>>>>>>*/

CREATE SEQUENCE IF NOT EXISTS product_id_seq;
PERFORM setval('product_id_seq', 1000000);
CREATE TABLE IF NOT EXISTS "product"
(
  id character varying(255) DEFAULT 'PCT'||nextval('product_id_seq') PRIMARY KEY,
  name character varying(255) NOT NULL,
  price NUMERIC(12, 2) NOT NULL,
  "type" character varying(255) NOT NULL,
  description VARCHAR(4000),
  is_active BOOLEAN NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  updated_at TIMESTAMP DEFAULT NOW()
);

CREATE SEQUENCE IF NOT EXISTS shop_id_seq;
PERFORM setval('shop_id_seq', 1000000);
CREATE TABLE IF NOT EXISTS "shop"
(
  id character varying(255) DEFAULT 'SHP'||nextval('shop_id_seq') PRIMARY KEY,
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
  shop_id character varying(255) NOT NULL,
  product_id character varying(255) NOT NULL,
  amount INT NOT NULL,

  FOREIGN KEY (shop_id) REFERENCES shop (id),
  FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE SEQUENCE IF NOT EXISTS employee_id_seq;
PERFORM setval('employee_id_seq', 1000000);
CREATE TABLE IF NOT EXISTS "employee"
(
  id character varying(255) DEFAULT 'EMP'||nextval('employee_id_seq') PRIMARY KEY,
  first_name character varying(255) NOT NULL,
  last_name character varying(255) NOT NULL,
  phone character varying(255) NOT NULL,
  email character varying(255) NOT NULL,

  shop_id character varying(255) NOT NULL,
  role character varying(255) NOT NULL,

  FOREIGN KEY (shop_id) REFERENCES shop (id)
);

CREATE SEQUENCE IF NOT EXISTS purchase_id_seq;
PERFORM setval('purchase_id_seq', 1000000);
CREATE TABLE IF NOT EXISTS "purchase"
(
  id character varying(255) DEFAULT 'PRS'||nextval('purchase_id_seq') PRIMARY KEY,
  shop_id character varying(255),
  employee_id character varying(255) NOT NULL,
  total_price NUMERIC(12, 2),

  registered_at TIMESTAMP,

  FOREIGN KEY (shop_id) REFERENCES shop (id),
  FOREIGN KEY (employee_id) REFERENCES employee (id)
);

CREATE TABLE IF NOT EXISTS "purchase_to_product"
(
  id SERIAL PRIMARY KEY,
  product_id character varying(255) NOT NULL,
  purchase_id character varying(255) NOT NULL,
  amount INT NOT NULL,

  FOREIGN KEY (product_id) REFERENCES product (id),
  FOREIGN KEY (purchase_id) REFERENCES purchase (id)
);

  /*Tables creation <<<<<<<<<<<<<<<<<<<<<*/

  /*Vies creation >>>>>>>>>>>>>>>>>>>>>>>*/

CREATE VIEW all_products_stocks AS
  SELECT pr.name as "Product Name", s.name as "Shop Name", s.city as "City", s.state as "State", sp.amount as "Amount of Product"
  FROM product pr
  LEFT JOIN shop_to_product sp ON pr.id = sp.product_id
  JOIN shop s ON s.id=sp.shop_id;

CREATE VIEW all_purchases AS
  SELECT pur.id as "Purchase Unique Number",
         pur.registered_at as "Time of Registration",
         CONCAT(e.first_name, ' ', e.last_name) as "Employee Name",
         e.role as "Employee Position",
         s.name as "Shop",
         s.city as "City"
  FROM purchase pur
  LEFT JOIN employee e ON pur.employee_id = e.id
  LEFT JOIN shop s ON pur.shop_id = s.id;

  /*Vies creation <<<<<<<<<<<<<<<<<<<<<*/

  /*Functions creation >>>>>>>>>>>>>>>>>>>>>>>*/

CREATE OR REPLACE FUNCTION get_rand_shop() RETURNS character varying(255) AS
$body$
  DECLARE
    rand_shop_id character varying(255);
  BEGIN
    SELECT s.id
    FROM shop s
    ORDER BY RANDOM()
    LIMIT 1
        INTO rand_shop_id;

    RETURN rand_shop_id;
  END;
$body$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_rand_employee() RETURNS character varying(255) AS
$body$
  DECLARE
    rand_employee_id character varying(255);
  BEGIN
    SELECT e.id
    FROM employee e
    ORDER BY RANDOM()
    LIMIT 1
        INTO rand_employee_id;

    RETURN rand_employee_id;
  END;
$body$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_rand_purchase() RETURNS character varying(255) AS
$body$
  DECLARE
    rand_purchase_id character varying(255);
  BEGIN
    SELECT p.id
    FROM purchase p
    ORDER BY RANDOM()
    LIMIT 1
        INTO rand_purchase_id;

    RETURN rand_purchase_id;
  END;
$body$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_rand_product() RETURNS character varying(255) AS
$body$
  DECLARE
    rand_product_id character varying(255);
  BEGIN
    SELECT p.id
    FROM product p
    ORDER BY RANDOM()
    LIMIT 1
        INTO rand_product_id;

    RETURN rand_product_id;
  END;
$body$
LANGUAGE plpgsql;
  /*Functions creation <<<<<<<<<<<<<<<<<<<<<*/