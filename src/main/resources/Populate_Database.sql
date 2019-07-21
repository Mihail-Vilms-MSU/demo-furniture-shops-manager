/* product table demo data */
insert into product (name, price, type, is_active, description)
values

	/* sofas -----------> */
	('Fava Sofa', 749.00, 'Sofa', true,
		'The Fava sofa offers up a perfect look and feel when you need a place to unwind.'),

	('Barcelona II Reclining Sofa', 799.00, 'Sofa', true,
		'The Barcelona II sofa offers the best of supportive comfort and classic style.'),

	('Caitlyn Sofa', 499.00, 'Sofa', true, 
		'If you''ve been looking for a smaller-sized sofa that doesn''t sacrifice style or comfort, then your search stops here!'),

	('Devon Reclining Sofa', 999.00, 'Sofa', true,
		'Firm webbed seating provides long-lasting support, and the convenient size maximizes space for your versatile lifestyle.'),

	('Alabama Reclining Sofa', 899.00, 'Sofa', true,
		'Upholstered in a deep brown microfibre, the Alabama will add richness and a soft touch to your home, while retaining a sense of toughness.'),
		
	('Bombay Leather Sofa', 1099.00, 'Sofa', true,
		'Founded on a solid and manufactured wood frame, this sofa strikes a streamlined silhouette with a pair of back cushions, track arms, and round tapered legs in a pecan finish.'),
	/* <---------- sofas */

	/* chairs -----------> */
	('Santos Accent Chair', 199.00, 'Chair', true,
		'The Santos accent chair features a chic, no-frills contemporary look with an armless design.'),

	('Crizia Chair', 699.00, 'Chair', true, 
		'Contemporary yet comfortable style is on display with the Crizia chair, featuring clean lines and cozy charm.'),

	('Stella Accent Chair', 399.00, 'Chair', true,
		'The Stella accent chair exudes comfort and classic club chair style updated for the modern home.'),

	('Anthena Chair', 329.00, 'Chair', true,
		'With the Anthena chair in charcoal, you get superior comfort without sacrificing style.'),

	('Zia Accent Chair', 429.00, 'Chair', true,
		'The Zia accent char boasts mid-century modern style for an on-trend retro look in your living area.'),

	('Paxton Wingback Chair', 213.00, 'Chair', true,
		'A classic design with a twist, this wingback chair is a perfect pick for outfitting a trendsetting teen’s room.'),
	/* <----------- chairs */

	/* beds -----------> */
	('Julianna Platform Bed', 138.00, 'Bed', true,
		'The centerpiece of a master suite or guest bedroom, the bed you choose is essential for anchoring your restful retreat''s style and function.'),

	('Her Majesty Upholstered Platform Bed', 280.00, 'Bed', true,
		'The wingback headboard is covered in elegant tufted linen.'),

	('Stromsburg Queen Platform Bed', 2999.00, 'Bed', true,
		'Taking cues from contemporary decor, this minimalist bedframe boasts a rectangular silhouette with protruding bedrails.'),
	
	('Brunner Platform Bed', 266.00, 'Bed', true,
		'Refresh your teen’s sleep cape with this stylish platform bed frame.'),
		
	('Houchins Upholstered Storage Platform Bed', 444.00, 'Bed', true,
		'The anchor of your bedroom''s style and the spot where you''ll be drifting off to sleep.'),
		
	('Alrai Upholstered Standard Bed', 753.00, 'Bed', true,
		'Steeped in streamlined style, this panel bed sets an approachable and on-trend foundation in your restful retreat.'),
	/* <----------- beds */
	
	/* tables -----------> */
	('Manila Lift-Top Coffee Table', 299.00, 'Table', true,
		'With its bold clean lines and dark grey finish, this occasional coffee table adds modern beauty to your home.'),

	('Madeira Coffee Table and Two End Tables', 647.00, 'Table', true,
		'This is a collection harmonizes traditional detail with functional design to accommodate today''s style.'),

	('Pinebrook Condo Coffee Table', 399.00, 'Table', true,
		'Urban Remix. Rustic styling and industrial flavour converge in the Pinebrook condo coffee table.'),

	('Gable Lift-Top Coffee Table', 499.00, 'Table', true,
		'Country-Style Details. The Gable coffee table brings contemporary style with a hint of rustic charm to your living room.'),

	('Tynan Dining Table', 314.00, 'Table', true, 
		'Bring your breakfast nook into the 21st century with this streamlined dining table.'),
	
	('Madeira Coffee Table - Walnut', 249.00, 'Table', true,
		'This coffee table is a collection harmonizes traditional detail with functional design to accommodate today''s style.');
	/* <----------- table */
  
    /* shop table demo data */
insert into shop (name, city, state, address, phone)
values
	('Echo Furniture', 'San Francisco', 'CA', '2222 21th St Noe Valley', '(444) 666-3344'),
	('Today’s Furniture', 'Portland', 'OR', '4244 Frank St', '(333) 667-2255'),
	('Furniture Envy', 'San Jose', 'CA', '300 Springs Rd', '(343) 988-2211'),
	('Room & Board', 'San Francisco', 'CA', '925 E Richmond Ave', '(888) 909-3432'),
	('Furniture Innovation', 'Las Vegas', 'NV', '1108 Hattie St', '(415) 666-3344'),
	('Bedroom & More', 'Portland', 'OR', '1329 Magnolia Ave', '(415) 666-3344'),
	('Monument', 'San Francisco', 'CA', '2900 88th St', '(415) 711-3344'),
	('Modern Details', 'Los Angeles', 'CA', '1500 55th St', '(415) 321-3564'),
	('Home Decor', 'Las Vegas', 'NV', '1050 Van Ness Ave', '(666) 444-5555'),
	('Furnish Green', 'San Jose', 'CA', '500 Lafayette St', '(222) 333-9999'), 
	('Home Union', 'Los Angeles', 'CA', '720 National Blvd', '(453) 888-1919'), 
	('Woodworks Furniture & Design', 'Los Angeles', 'CA', '1320 Ohio Ave', '(111) 090-7676');
	 
	 
INSERT INTO shop_to_product(product_id, shop_id, amount)
SELECT p.id, s.id, 100
FROM product p, shop s;

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


DO $$
BEGIN
	FOR i IN 1..120 LOOP


	  INSERT INTO purchase (employee_id, shop_id, registered_at)
	  SELECT e.id, e.shop_id, timestamp '2019-03-01 00:00:00' + random() * (timestamp '2019-03-31 23:59:59' - timestamp '2019-03-01 00:00:00')
	  FROM employee e
	  WHERE e.id IN (
		select get_rand_employee()
	  );

	INSERT INTO purchase_to_product (purchase_id, product_id, amount)
	SELECT get_rand_purchase(), get_rand_product(), floor(random() * 4 + 1);
	
	END LOOP;
END;$$


