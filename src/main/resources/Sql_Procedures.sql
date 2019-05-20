CREATE OR REPLACE PROCEDURE drop_all()
LANGUAGE plpgsql
AS $$
  BEGIN
  DROP FUNCTION IF EXISTS get_rand_shop();

  DROP VIEW IF EXISTS all_purchases CASCADE;
  DROP VIEW IF EXISTS all_products_stocks CASCADE;

  DROP TABLE IF EXISTS purchase_to_product CASCADE;
  DROP TABLE IF EXISTS shop_to_product CASCADE;
  DROP TABLE IF EXISTS purchase CASCADE;
  DROP TABLE IF EXISTS employee CASCADE;
  DROP TABLE IF EXISTS product CASCADE;
  DROP TABLE IF EXISTS shop CASCADE;

  DROP SEQUENCE IF EXISTS product_id_seq;
  DROP SEQUENCE IF EXISTS shop_id_seq;
  DROP SEQUENCE IF EXISTS employee_id_seq;
  DROP SEQUENCE IF EXISTS purchase_id_seq;

  COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE create_all()
LANGUAGE plpgsql
AS $$
  BEGIN

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


COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE drop_and_create_all()
LANGUAGE plpgsql
AS $$
BEGIN
  CALL drop_all();
  CALL create_all();
COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE fill_demo_data_product()
LANGUAGE plpgsql
AS $$
BEGIN
  /* product table demo data */
  insert into product (name, price, type, description, is_active)
  values
         /* sofas -----------> */
      ('Fava Sofa', 749.00, 'Sofa',
       'The Fava sofa offers up a perfect look and feel when you need a place to unwind.',
       true),

      ('Barcelona II Reclining Sofa', 799.00, 'Sofa',
       'The Barcelona II sofa offers the best of supportive comfort and classic style.',
       true),

      ('Caitlyn Sofa', 499.00, 'Sofa',
       'If you''ve been looking for a smaller-sized sofa that doesn''t sacrifice style or comfort, then your search stops here!',
       true),

      ('Devon Reclining Sofa', 999.00, 'Sofa',
       'Firm webbed seating provides long-lasting support, and the convenient size maximizes space for your versatile lifestyle.',
       true),

      ('Alabama Reclining Sofa', 899.00, 'Sofa',
       'Upholstered in a deep brown microfibre, the Alabama will add richness and a soft touch to your home, while retaining a sense of toughness.',
       true),
         /* <---------- sofas */

      /* chairs -----------> */
      ('Santos Accent Chair', 199.00, 'Chair',
       'The Santos accent chair features a chic, no-frills contemporary look with an armless design.',
       true),

      ('Crizia Chair', 699.00, 'Chair',
       'Contemporary yet comfortable style is on display with the Crizia chair, featuring clean lines and cozy charm.',
       true),

      ('Stella Accent Chair', 399.00, 'Chair',
       'The Stella accent chair exudes comfort and classic club chair style updated for the modern home.',
       true),

      ('Anthena Chair', 329.00, 'Chair',
       'With the Anthena chair in charcoal, you get superior comfort without sacrificing style.',
       true),

      ('Zia Accent Chair', 429.00, 'Chair',
       'The Zia accent char boasts mid-century modern style for an on-trend retro look in your living area.',
       true),
         /* <----------- chairs */

      /* tables -----------> */
      ('Manila Lift-Top Coffee Table', 299.00, 'Table',
       'With its bold clean lines and dark grey finish, this occasional coffee table adds modern beauty to your home.',
       true),

      ('Madeira Coffee Table and Two End Tables', 647.00, 'Table',
       'This is a collection harmonizes traditional detail with functional design to accommodate today''s style.',
       true),

      ('Pinebrook Condo Coffee Table', 399.00, 'Table',
       'Urban Remix. Rustic styling and industrial flavour converge in the Pinebrook condo coffee table.',
       true),

      ('Gable Lift-Top Coffee Table', 499.00, 'Table',
       'Country-Style Details. The Gable coffee table brings contemporary style with a hint of rustic charm to your living room.',
       true),

      ('Madeira Coffee Table - Walnut', 249.00, 'Table',
       'This coffee table is a collection harmonizes traditional detail with functional design to accommodate today''s style.',
       true);
  /* <----------- table */
END;
$$;

CREATE OR REPLACE PROCEDURE fill_demo_data_shop()
LANGUAGE plpgsql
AS $$
BEGIN
  /* shop table demo data */
  insert into shop (name, city, state, address, phone, created_at, updated_at)
  values
     ('Echo Furniture', 'San Francisco', 'CA', '2222 21th St Noe Valley', '(444) 666-3344', null, null),
     ('Today’s Furniture', 'Portland', 'OR', '4244 Frank St', '(333) 667-2255', null, null),
     ('Furniture Envy', 'San Jose', 'CA', '300 Springs Rd', '(343) 988-2211', null, null),
     ('Room & Board', 'San Francisco ', 'CA', '925 E Richmond Ave', '(888) 909-3432', null, null),
     ('Furniture Innovation', 'Las Vegas', 'NV', '1108 Hattie St', '(415) 666-3344', null, null),
     ('Bedroom & More', 'Portland', 'OR', '1329 Magnolia Ave', '(415) 666-3344', null, null),
     ('Monument', 'San Francisco', 'CA', '2900 88th St', '(415) 711-3344', null, null),
     ('Modern Details', 'Los Angeles', 'CA', '1500 55th St', '(415) 321-3564', null, null);
END;
$$;

CREATE OR REPLACE PROCEDURE fill_demo_data_shop_to_product()
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO shop_to_product(product_id, shop_id, amount)
    SELECT p.id, s.id, 100
    FROM product p, shop s;
END;
$$;

CREATE OR REPLACE PROCEDURE fill_demo_data_employee()
LANGUAGE plpgsql
AS $$
BEGIN
  /* employees table */
  insert into employee (first_name, last_name, email, phone, shop_id, role)
  values
         ('Larry', 'Popp', 'LarryJPopp@superrito.com', '440-498-9021', (select get_rand_shop()), 'Assistant'),
         ('Mary', 'Crocker', 'MaryBCrocker@einrot.com', '570-823-2639', (select get_rand_shop()), 'Administrator'),
         ('Bette', 'Quist', 'BetteWQuist@fleckens.hu', '209-727-8580', (select get_rand_shop()), 'Administrator'),
         ('Elizabeth', 'Miller', 'ElizabethJMiller@cuvox.de', '315-742-4912', (select get_rand_shop()), 'Merchandizer'),
         ('Robert', 'Bernal', 'RobertJBernal@dayrep.com', '907-886-5099', (select get_rand_shop()), 'Merchandizer'),
         ('Rachel', 'Graber', 'RachelJGraber@cuvox.de', '504-457-6471', (select get_rand_shop()), 'Assistant'),
         ('Elsie', 'Garcia', 'ElsieBGarcia@rhyta.com', '516-869-6843', (select get_rand_shop()), 'Assistant'),
         ('Marshall', 'Miller', 'MarshallLMiller@dayrep.com', '646-955-9252', (select get_rand_shop()), 'Merchandizer'),
         ('Bryan', 'Carrasco', 'BryanMCarrasco@dayrep.com', '405-208-0219', (select get_rand_shop()), 'Assistant'),
         ('Christopher', 'Tyrrell', 'ChristopherMTyrrell@teleworm.us', '586-413-0849', (select get_rand_shop()), 'Merchandizer'),
         ('Rita', 'Rodriguez', 'RitaCRodriguez@superrito.com', '954-703-1261', (select get_rand_shop()), 'Assistant'),
         ('Erma', 'Darnell', 'ErmaCDarnell@fleckens.hu', '973-776-4855', (select get_rand_shop()), 'Administrator'),
         ('Misty', 'Monroe', 'MistyRMonroe@teleworm.us', '773-625-6720', (select get_rand_shop()), 'Administrator'),
         ('William', 'Kenyon', 'WilliamCKenyon@fleckens.hu', '562-866-0411', (select get_rand_shop()), 'Merchandizer'),
         ('Robert', 'Burgess', 'RobertDBurgess@teleworm.us', '903-780-0837', (select get_rand_shop()), 'Assistant'),
         ('Virginia', 'Northern', 'VirginiaRNorthern@dayrep.com', '301-705-0126', (select get_rand_shop()), 'Assistant'),
         ('Valerie', 'Jenkins', 'ValerieRJenkins@rhyta.com', '419-229-7737', (select get_rand_shop()), 'Assistant'),
         ('Jermaine', 'Schroeder', 'JermaineGSchroeder@rhyta.com', '973-319-5118', (select get_rand_shop()), 'Merchandizer'),
         ('Cordell', 'Evans', 'CordellCEvans@rhyta.com', '231-678-7547', (select get_rand_shop()), 'Assistant'),
         ('Sarah', 'Vega', 'SarahTVega@jourrapide.com', '971-215-6547', (select get_rand_shop()), 'Assistant'),
         ('Carlos', 'Jolly', 'CarlosEJolly@rhyta.com', '920-645-3561', (select get_rand_shop()), 'Merchandizer'),
         ('Goldie', 'Damian', 'GoldieEDamian@cuvox.de', '415-368-2982', (select get_rand_shop()), 'Administrator'),
         ('Paul', 'Brennen', 'PaulRBrennen@cuvox.de', '312-482-5361', (select get_rand_shop()), 'Administrator'),
         ('Carol', 'Perez', 'CarolJPerez@gustr.com', '775-548-1253', (select get_rand_shop()), 'Merchandizer'),
         ('Steven', 'Simmons', 'StevenHSimmons@teleworm.us', '608-314-5459', (select get_rand_shop()), 'Assistant'),
         ('Angela', 'Helton', 'AngelaCHelton@rhyta.com', '509-328-1115', (select get_rand_shop()), 'Assistant'),
         ('Nikita', 'Rodriquez', 'NikitaRRodriquez@dayrep.com', '606-313-6796', (select get_rand_shop()), 'Assistant'),
         ('Jenifer', 'Lynch', 'JeniferHLynch@einrot.com', '205-609-5317', (select get_rand_shop()), 'Administrator'),
         ('Mary', 'Jackson', 'MaryRJackson@teleworm.us', '347-232-6849', (select get_rand_shop()), 'Administrator'),
         ('Trina', 'McCoy', 'TrinaEMcCoy@rhyta.com', '323-384-1993', (select get_rand_shop()), 'Administrator'),
         ('Kelly', 'Blakely', 'KellyJBlakely@teleworm.us', '216-450-3322', (select get_rand_shop()), 'Assistant'),
         ('James', 'Richardson', 'JamesERichardson@gustr.com', '979-775-4554', (select get_rand_shop()), 'Administrator'),
         ('Eleanor', 'Ledbetter', 'EleanorRLedbetter@einrot.com', '402-621-3631', (select get_rand_shop()), 'Assistant'),
         ('John', 'Davidson', 'JohnHDavidson@jourrapide.com', '808-276-7826', (select get_rand_shop()), 'Merchandizer'),
         ('Shawn', 'Gallager', 'ShawnCGallager@jourrapide.com', '916-427-2761', (select get_rand_shop()), 'Merchandizer'),
         ('Sallie', 'Duncan', 'SallieRDuncan@jourrapide.com', '231-414-3621', (select get_rand_shop()), 'Administrator'),
         ('Patricia', 'Knight', 'PatriciaEKnight@einrot.com', '817-378-6647', (select get_rand_shop()), 'Assistant'),
         ('Lisa', 'Espinosa', 'LisaEEspinosa@rhyta.com', '806-495-7018', (select get_rand_shop()), 'Merchandizer'),
         ('James', 'Willard', 'JamesAWillard@superrito.com', '865-595-6816', (select get_rand_shop()), 'Merchandizer'),
         ('Carolyn', 'Swindle', 'CarolynRSwindle@einrot.com', '917-205-8430', (select get_rand_shop()), 'Assistant'),
         ('Lisa', 'Scott', 'LisaPScott@cuvox.de', '267-545-5574', (select get_rand_shop()), 'Administrator'),
         ('Terry', 'Coen', 'TerryMCoen@gustr.com', '310-677-6852', (select get_rand_shop()), 'Merchandizer'),
         ('Gregorio', 'Taylor', 'GregorioETaylor@armyspy.com', '406-433-9709', (select get_rand_shop()), 'Assistant'),
         ('Mildred', 'Wilbourn', 'MildredAWilbourn@einrot.com', '910-667-1751', (select get_rand_shop()), 'Merchandizer'),
         ('Maria', 'Mayton', 'MariaTMayton@jourrapide.com', '828-504-6176', (select get_rand_shop()), 'Merchandizer'),
         ('Jennifer', 'Morgan', 'JenniferEMorgan@superrito.com', '606-239-8274', (select get_rand_shop()), 'Merchandizer'),
         ('Mildred', 'Cruz', 'MildredCCruz@gustr.com', '646-312-7255', (select get_rand_shop()), 'Merchandizer'),
         ('Judson', 'Lemieux', 'JudsonPLemieux@cuvox.de', '347-282-2746', (select get_rand_shop()), 'Administrator'),
         ('James', 'Watson', 'JamesVWatson@jourrapide.com', '336-389-2376', (select get_rand_shop()), 'Merchandizer'),
         ('Marilyn', 'Herbert', 'MarilynJHerbert@fleckens.hu', '650-997-9836', (select get_rand_shop()), 'Administrator'),
         ('Jennifer', 'Baker', 'JenniferHBaker@rhyta.com', '508-636-8386', (select get_rand_shop()), 'Administrator'),
         ('Lucy', 'Holland', 'LucyWHolland@cuvox.de', '716-740-5635', (select get_rand_shop()), 'Assistant'),
         ('Thomas', 'Cheney', 'ThomasLCheney@teleworm.us', '217-699-9422', (select get_rand_shop()), 'Administrator'),
         ('Jeffery', 'Gulick', 'JefferyBGulick@teleworm.us', '901-896-1930', (select get_rand_shop()), 'Administrator'),
         ('Daniel', 'Oviedo', 'DanielROviedo@cuvox.de', '972-834-8553', (select get_rand_shop()), 'Administrator'),
         ('Micah', 'Carroll', 'MicahKCarroll@rhyta.com', '313-336-0245', (select get_rand_shop()), 'Merchandizer'),
         ('Wilma', 'Keith', 'WilmaDKeith@dayrep.com', '740-801-9956', (select get_rand_shop()), 'Administrator'),
         ('Kathryn', 'Hagan', 'KathrynRHagan@fleckens.hu', '412-576-5464', (select get_rand_shop()), 'Assistant'),
         ('Lydia', 'Weeks', 'LydiaJWeeks@fleckens.hu', '407-529-6783', (select get_rand_shop()), 'Assistant'),
         ('Christine', 'Heal', 'ChristineDHeal@teleworm.us', '973-628-1650', (select get_rand_shop()), 'Administrator'),
         ('Clara', 'Reyes', 'ClaraMReyes@cuvox.de', '507-546-3456', (select get_rand_shop()), 'Administrator'),
         ('David', 'Sammons', 'DavidJSammons@cuvox.de', '740-477-6933', (select get_rand_shop()), 'Administrator'),
         ('James', 'Sheppard', 'JamesRSheppard@dayrep.com', '660-237-6174', (select get_rand_shop()), 'Assistant'),
         ('Randall', 'Everette', 'RandallDEverette@teleworm.us', '215-351-3376', (select get_rand_shop()), 'Administrator'),
         ('Kenneth', 'Carter', 'KennethDCarter@superrito.com', '701-273-7991', (select get_rand_shop()), 'Merchandizer'),
         ('Justin', 'Lewis', 'JustinMLewis@rhyta.com', '408-716-0115', (select get_rand_shop()), 'Administrator'),
         ('Martin', 'Christopherso', 'MartinBChristopherso@einrot.com', '510-339-5021', (select get_rand_shop()), 'Assistant'),
         ('Harvey', 'Wallace', 'HarveyLWallace@superrito.com', '401-296-6079', (select get_rand_shop()), 'Administrator'),
         ('Jessica', 'Lamar', 'JessicaSLamar@rhyta.com', '360-343-2494', (select get_rand_shop()), 'Administrator'),
         ('Jacqueline', 'Landon', 'JacquelineCLandon@cuvox.de', '804-520-2478', (select get_rand_shop()), 'Assistant'),
         ('Richard', 'Cahill', 'RichardCCahill@armyspy.com', '212-466-0360', (select get_rand_shop()), 'Merchandizer'),
         ('Michaela', 'McDaniel', 'MichaelaLMcDaniel@jourrapide.com', '412-977-6787', (select get_rand_shop()), 'Merchandizer'),
         ('Jo', 'Waller', 'JoWWaller@dayrep.com', '269-764-8453', (select get_rand_shop()), 'Administrator'),
         ('Joseph', 'Foster', 'JosephRFoster@superrito.com', '309-401-1671', (select get_rand_shop()), 'Merchandizer'),
         ('Megan', 'Crain', 'MeganSCrain@dayrep.com', '724-754-9027', (select get_rand_shop()), 'Merchandizer'),
         ('Sabrina', 'Lee', 'SabrinaLLee@gustr.com', '781-852-2235', (select get_rand_shop()), 'Merchandizer'),
         ('Tammy', 'Garza', 'TammyJGarza@jourrapide.com', '909-207-7621', (select get_rand_shop()), 'Administrator'),
         ('Elma', 'Pearsall', 'ElmaJPearsall@dayrep.com', '832-216-1606', (select get_rand_shop()), 'Merchandizer'),
         ('David', 'Henderson', 'DavidBHenderson@gustr.com', '405-483-7597', (select get_rand_shop()), 'Administrator'),
         ('Frances', 'Schweigert', 'FrancesCSchweigert@jourrapide.com', '765-737-8653', (select get_rand_shop()), 'Assistant'),
         ('Pedro', 'Ward', 'PedroAWard@gustr.com', '863-654-3623', (select get_rand_shop()), 'Administrator'),
         ('Timothy', 'Shockley', 'TimothyGShockley@teleworm.us', '773-393-1886', (select get_rand_shop()), 'Merchandizer'),
         ('Charles', 'Harvey', 'CharlesGHarvey@cuvox.de', '562-558-1822', (select get_rand_shop()), 'Assistant'),
         ('Lupe', 'Berg', 'LupeKBerg@rhyta.com', '215-594-4008', (select get_rand_shop()), 'Assistant'),
         ('Robert', 'Burket', 'RobertEBurket@einrot.com', '312-260-7299', (select get_rand_shop()), 'Merchandizer'),
         ('Georgia', 'Kappler', 'GeorgiaAKappler@teleworm.us', '214-915-9392', (select get_rand_shop()), 'Assistant'),
         ('Lakendra', 'McNamara', 'LakendraSMcNamara@gustr.com', '201-635-1187', (select get_rand_shop()), 'Assistant'),
         ('Marcus', 'Bueno', 'MarcusRBueno@teleworm.us', '509-453-3810', (select get_rand_shop()), 'Administrator'),
         ('Kathleen', 'Bermudez', 'KathleenSBermudez@fleckens.hu', '662-456-9177', (select get_rand_shop()), 'Assistant'),
         ('William', 'Gomes', 'WilliamJGomes@teleworm.us', '310-340-3696', (select get_rand_shop()), 'Administrator'),
         ('Jeffery', 'Chatham', 'JefferyIChatham@rhyta.com', '724-509-8772', (select get_rand_shop()), 'Merchandizer'),
         ('Felicia', 'Leach', 'FeliciaJLeach@armyspy.com', '216-462-8653', (select get_rand_shop()), 'Assistant'),
         ('Oscar', 'Dillman', 'OscarKDillman@cuvox.de', '858-395-9257', (select get_rand_shop()), 'Administrator'),
         ('Luis', 'Belle', 'LuisABelle@rhyta.com', '979-647-1546', (select get_rand_shop()), 'Merchandizer'),
         ('Michael', 'Couch', 'MichaelECouch@armyspy.com', '870-420-5207', (select get_rand_shop()), 'Administrator'),
         ('Roy', 'Edwards', 'RoyBEdwards@teleworm.us', '507-296-8452', (select get_rand_shop()), 'Merchandizer'),
         ('Patty', 'Hill', 'PattyDHill@jourrapide.com', '802-706-3987', (select get_rand_shop()), 'Assistant'),
         ('Margie', 'Decker', 'MargieTDecker@einrot.com', '323-432-1625', (select get_rand_shop()), 'Administrator'),
         ('Keith', 'Strode', 'KeithMStrode@superrito.com', '860-667-6906', (select get_rand_shop()), 'Merchandizer'),
         ('Leonard', 'King', 'LeonardMKing@dayrep.com', '562-705-5686', (select get_rand_shop()), 'Merchandizer'),
         ('Reva', 'Rogers', 'RevaWRogers@cuvox.de', '218-270-2866', (select get_rand_shop()), 'Merchandizer'),
         ('Deborah', 'Bechtold', 'DeborahEBechtold@cuvox.de', '321-205-7576', (select get_rand_shop()), 'Administrator'),
         ('Suzanne', 'Kern', 'SuzanneFKern@cuvox.de', '928-819-8335', (select get_rand_shop()), 'Administrator'),
         ('Michael', 'Cleveland', 'MichaelACleveland@teleworm.us', '318-396-4496', (select get_rand_shop()), 'Merchandizer'),
         ('Nicholas', 'Jones', 'NicholasMJones@rhyta.com', '360-901-4287', (select get_rand_shop()), 'Administrator'),
         ('Rachel', 'Lugo', 'RachelJLugo@armyspy.com', '703-329-0719', (select get_rand_shop()), 'Administrator'),
         ('Lori', 'Crabtree', 'LoriSCrabtree@einrot.com', '919-394-6025', (select get_rand_shop()), 'Administrator'),
         ('Jeff', 'Davis', 'JeffKDavis@superrito.com', '918-543-2503', (select get_rand_shop()), 'Assistant'),
         ('Richard', 'Maillet', 'RichardDMaillet@armyspy.com', '812-939-9345', (select get_rand_shop()), 'Administrator'),
         ('Andre', 'Addison', 'AndreBAddison@superrito.com', '410-933-1416', (select get_rand_shop()), 'Merchandizer'),
         ('Miguel', 'Jackson', 'MiguelDJackson@armyspy.com', '979-318-2072', (select get_rand_shop()), 'Administrator'),
         ('Lauren', 'Stone', 'LaurenRStone@teleworm.us', '703-614-6619', (select get_rand_shop()), 'Merchandizer'),
         ('Mary', 'Drummer', 'MaryFDrummer@cuvox.de', '864-410-3277', (select get_rand_shop()), 'Administrator'),
         ('John', 'Hartford', 'JohnEHartford@cuvox.de', '646-463-1769', (select get_rand_shop()), 'Assistant'),
         ('Jeremy', 'Spradlin', 'JeremyNSpradlin@fleckens.hu', '817-560-9106', (select get_rand_shop()), 'Administrator'),
         ('Catherine', 'Silva', 'CatherineHSilva@fleckens.hu', '765-292-5181', (select get_rand_shop()), 'Merchandizer'),
         ('Larry', 'Harris', 'LarryMHarris@dayrep.com', '916-687-6828', (select get_rand_shop()), 'Merchandizer'),
         ('Matt', 'Jones', 'MattJJones@superrito.com', '816-635-9835', (select get_rand_shop()), 'Merchandizer'),
         ('Sheila', 'Greiner', 'SheilaGGreiner@jourrapide.com', '217-414-1103', (select get_rand_shop()), 'Assistant'),
         ('Kathryn', 'Johnston', 'KathrynMJohnston@dayrep.com', '972-773-8927', (select get_rand_shop()), 'Merchandizer');
END;
$$;

CREATE OR REPLACE PROCEDURE fill_demo_data_purchase(INT)
LANGUAGE plpgsql
AS $$
  BEGIN
    BEGIN
      FOR i IN 1..$1 LOOP

        BEGIN
          INSERT INTO purchase (employee_id, shop_id, registered_at)
          SELECT e.id, e.shop_id, timestamp '2019-03-01 00:00:00' + random() * (timestamp '2019-03-31 23:59:59' - timestamp '2019-03-01 00:00:00')
          FROM employee e
          WHERE e.id IN (
            select get_rand_employee()
          );
        END;

      END LOOP;
    END;
  COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE fill_demo_data_purchase_to_product(INT)
LANGUAGE plpgsql
AS $$
BEGIN

  INSERT INTO purchase_to_product (purchase_id, product_id, amount)
    SELECT get_rand_purchase(), get_rand_product(), floor(random() * 4 + 1);

  FOR i IN 1..$1 LOOP
--     INSERT INTO purchase_to_product (purchase_id, product_id, amount)
--     SELECT floor(random() * 2000 + 1), floor(random() * 15 + 1), floor(random() * 4 + 1);
  END LOOP;
  COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE fill_demo_data()
LANGUAGE plpgsql
AS $$
BEGIN
  CALL fill_demo_data_product();
  CALL fill_demo_data_shop();
  CALL fill_demo_data_employee();
  CALL fill_demo_data_shop_to_product();
  /* CALL fill_demo_data_purchase(2000);*/ /* TODO Magic number 2000-number of all purchases for demonstrational data */
  /* CALL fill_demo_data_purchase_to_product(7000);*/ /* TODO Magic number 7000-number of all product positions for demonstrational data*/
  COMMIT;
END;
$$;

  /*Triggers creation >>>>>>>>>>>>>>>>>>>*/

  CREATE OR REPLACE FUNCTION trigger_set_timestamp()
    RETURNS TRIGGER AS $$
    BEGIN
      NEW.updated_at = NOW();
      RETURN NEW;
    END;
    $$ LANGUAGE 'plpgsql';

  CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON product
    FOR EACH ROW
    EXECUTE PROCEDURE trigger_set_timestamp();

  /*Triggers creation <<<<<<<<<<<<<<<<<<<<<*/
