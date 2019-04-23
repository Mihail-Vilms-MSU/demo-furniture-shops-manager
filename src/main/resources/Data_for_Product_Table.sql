 /* product table demo data */
insert into product (id, name, price, type, description, is_active, created_at, updated_at)

values
  /* sofas -----------> */
(1001, 'Fava Sofa', 749.00, 'Sofa',
  'The Fava sofa offers up a perfect look and feel when you need a place to unwind.',
  true, null, null),

(1002, 'Barcelona II Reclining Sofa', 799.00, 'Sofa',
  'The Barcelona II sofa offers the best of supportive comfort and classic style.',
  true, null, null),

(1003, 'Caitlyn Sofa', 499.00, 'Sofa',
  'If you''ve been looking for a smaller-sized sofa that doesn''t sacrifice style or comfort, then your search stops here!',
  true, null, null),

(1004, 'Devon Reclining Sofa', 999.00, 'Sofa',
  'Firm webbed seating provides long-lasting support, and the convenient size maximizes space for your versatile lifestyle.',
  true, null, null),

(1005, 'Alabama Reclining Sofa', 899.00, 'Sofa',
  'Upholstered in a deep brown microfibre, the Alabama will add richness and a soft touch to your home, while retaining a sense of toughness.',
  true, null, null),
  /* <---------- sofas */

  /* chairs -----------> */
(1006, 'Santos Accent Chair', 199.00, 'Chair',
  'The Santos accent chair features a chic, no-frills contemporary look with an armless design.',
  true, null, null),

(1007, 'Crizia Chair', 699.00, 'Chair',
  'Contemporary yet comfortable style is on display with the Crizia chair, featuring clean lines and cozy charm.',
  true, null, null),

(1008, 'Stella Accent Chair', 399.00, 'Chair',
  'The Stella accent chair exudes comfort and classic club chair style updated for the modern home.',
  true, null, null),

(1009, 'Anthena Chair', 329.00, 'Chair',
  'With the Anthena chair in charcoal, you get superior comfort without sacrificing style.',
  true, null, null),

(1010, 'Zia Accent Chair', 429.00, 'Chair',
  'The Zia accent chair boasts mid-century modern style for an on-trend retro look in your living area.',
  true, null, null);

 /* shop table demo data */
insert into shop (id, name, city, state, address, phone, created_at, updated_at)
values
(1001, 'Echo Furniture', 'San Francisco', 'CA', '2222 21th St Noe Valley', '(444) 666-3344', null, null),

(1002, 'Today’s Furniture', 'Portland', 'OR', '4244 Frank St', '(333) 667-2255', null, null),

(1003, 'Furniture Envy', 'San Jose', 'CA', '300 Springs Rd', '(343) 988-2211', null, null),

(1004, 'Room & Board', 'San Francisco ', 'CA', '925 E Richmond Ave', '(888) 909-3432', null, null),

(1005, 'Furniture Innovation', 'Las Vegas', 'NV', '1108 Hattie St', '(415) 666-3344', null, null),

(1006, 'Bedroom & More', 'Portland', 'OR', '1329 Magnolia Ave', '(415) 666-3344', null, null);

(1007, 'Monument', 'San Francisco', 'CA', '2900 88th St', '(415) 711-3344', null, null);

(1008, 'Modern Details', 'Los Angeles', 'CA', '1500 55th St', '(415) 321-3564', null, null);

 /* shop_to_product table demo data */
insert into shop_to_product (shop_id, product_id, amount)
values
(1001, 1001, 50),
(1001, 1002, 50),
(1001, 1003, 50),
(1001, 1004, 50),
(1001, 1005, 50),
(1001, 1006, 100),
(1001, 1007, 100),
(1001, 1008, 100),
(1001, 1009, 100),
(1001, 10010, 100),

(1002, 1001, 30),
(1002, 1002, 30),
(1002, 1003, 30),
(1002, 1006, 50),
(1002, 1007, 50),
(1002, 1008, 50),
(1002, 1009, 50),
(1002, 1010, 50),

(1003, 1002, 20),
(1003, 1003, 20),
(1003, 1004, 20),
(1003, 1005, 20),
(1003, 1006, 100),
(1003, 1007, 100),
(1003, 1008, 50),
(1003, 1009, 50),
(1003, 1010, 100);


/* employees table */
insert into employee (id, first_name, last_name, email, phone, shop_id, role)

values

(1001, 'Larry', 'Popp', 'LarryJPopp@superrito.com', '440-498-9021', 1008, 'Assistant'),
(1002, 'Mary', 'Crocker', 'MaryBCrocker@einrot.com', '570-823-2639', 1001, 'Administrator'),
(1003, 'Bette', 'Quist', 'BetteWQuist@fleckens.hu', '209-727-8580', 1007, 'Administrator'),
(1004, 'Elizabeth', 'Miller', 'ElizabethJMiller@cuvox.de', '315-742-4912', 1004, 'Merchandizer'),
(1005, 'Robert', 'Bernal', 'RobertJBernal@dayrep.com', '907-886-5099', 1006, 'Merchandizer'),
(1006, 'Rachel', 'Graber', 'RachelJGraber@cuvox.de', '504-457-6471', 1004, 'Assistant'),
(1007, 'Elsie', 'Garcia', 'ElsieBGarcia@rhyta.com', '516-869-6843', 1002, 'Assistant'),
(1008, 'Marshall', 'Miller', 'MarshallLMiller@dayrep.com', '646-955-9252', 1008, 'Merchandizer'),
(1009, 'Bryan', 'Carrasco', 'BryanMCarrasco@dayrep.com', '405-208-0219', 1005, 'Assistant'),
(1010, 'Christopher', 'Tyrrell', 'ChristopherMTyrrell@teleworm.us', '586-413-0849', 1004, 'Merchandizer'),
(1011, 'Rita', 'Rodriguez', 'RitaCRodriguez@superrito.com', '954-703-1261', 1004, 'Assistant'),
(1012, 'Erma', 'Darnell', 'ErmaCDarnell@fleckens.hu', '973-776-4855', 1007, 'Administrator'),
(1013, 'Misty', 'Monroe', 'MistyRMonroe@teleworm.us', '773-625-6720', 1002, 'Administrator'),
(1014, 'William', 'Kenyon', 'WilliamCKenyon@fleckens.hu', '562-866-0411', 1001, 'Merchandizer'),
(1015, 'Robert', 'Burgess', 'RobertDBurgess@teleworm.us', '903-780-0837', 1002, 'Assistant'),
(1016, 'Virginia', 'Northern', 'VirginiaRNorthern@dayrep.com', '301-705-0126', 1002, 'Assistant'),
(1017, 'Valerie', 'Jenkins', 'ValerieRJenkins@rhyta.com', '419-229-7737', 1001, 'Assistant'),
(1018, 'Jermaine', 'Schroeder', 'JermaineGSchroeder@rhyta.com', '973-319-5118', 1001, 'Merchandizer'),
(1019, 'Cordell', 'Evans', 'CordellCEvans@rhyta.com', '231-678-7547', 1003, 'Assistant'),
(1020, 'Sarah', 'Vega', 'SarahTVega@jourrapide.com', '971-215-6547', 1006, 'Assistant'),
(1021, 'Carlos', 'Jolly', 'CarlosEJolly@rhyta.com', '920-645-3561', 1001, 'Merchandizer'),
(1022, 'Goldie', 'Damian', 'GoldieEDamian@cuvox.de', '415-368-2982', 1001, 'Administrator'),
(1023, 'Paul', 'Brennen', 'PaulRBrennen@cuvox.de', '312-482-5361', 1001, 'Administrator'),
(1024, 'Carol', 'Perez', 'CarolJPerez@gustr.com', '775-548-1253', 1004, 'Merchandizer'),
(1025, 'Steven', 'Simmons', 'StevenHSimmons@teleworm.us', '608-314-5459', 1007, 'Assistant'),
(1026, 'Angela', 'Helton', 'AngelaCHelton@rhyta.com', '509-328-1115', 1004, 'Assistant'),
(1027, 'Nikita', 'Rodriquez', 'NikitaRRodriquez@dayrep.com', '606-313-6796', 1004, 'Assistant'),
(1028, 'Jenifer', 'Lynch', 'JeniferHLynch@einrot.com', '205-609-5317', 1003, 'Administrator'),
(1029, 'Mary', 'Jackson', 'MaryRJackson@teleworm.us', '347-232-6849', 1008, 'Administrator'),
(1030, 'Trina', 'McCoy', 'TrinaEMcCoy@rhyta.com', '323-384-1993', 1001, 'Administrator'),
(1031, 'Kelly', 'Blakely', 'KellyJBlakely@teleworm.us', '216-450-3322', 1008, 'Assistant'),
(1032, 'James', 'Richardson', 'JamesERichardson@gustr.com', '979-775-4554', 1002, 'Administrator'),
(1033, 'Eleanor', 'Ledbetter', 'EleanorRLedbetter@einrot.com', '402-621-3631', 1007, 'Assistant'),
(1034, 'John', 'Davidson', 'JohnHDavidson@jourrapide.com', '808-276-7826', 1008, 'Merchandizer'),
(1035, 'Shawn', 'Gallager', 'ShawnCGallager@jourrapide.com', '916-427-2761', 1001, 'Merchandizer'),
(1036, 'Sallie', 'Duncan', 'SallieRDuncan@jourrapide.com', '231-414-3621', 1001, 'Administrator'),
(1037, 'Patricia', 'Knight', 'PatriciaEKnight@einrot.com', '817-378-6647', 1002, 'Assistant'),
(1038, 'Lisa', 'Espinosa', 'LisaEEspinosa@rhyta.com', '806-495-7018', 1005, 'Merchandizer'),
(1039, 'James', 'Willard', 'JamesAWillard@superrito.com', '865-595-6816', 1008, 'Merchandizer'),
(1040, 'Carolyn', 'Swindle', 'CarolynRSwindle@einrot.com', '917-205-8430', 1005, 'Assistant'),
(1041, 'Lisa', 'Scott', 'LisaPScott@cuvox.de', '267-545-5574', 1001, 'Administrator'),
(1042, 'Terry', 'Coen', 'TerryMCoen@gustr.com', '310-677-6852', 1003, 'Merchandizer'),
(1043, 'Gregorio', 'Taylor', 'GregorioETaylor@armyspy.com', '406-433-9709', 1007, 'Assistant'),
(1044, 'Mildred', 'Wilbourn', 'MildredAWilbourn@einrot.com', '910-667-1751', 1003, 'Merchandizer'),
(1045, 'Maria', 'Mayton', 'MariaTMayton@jourrapide.com', '828-504-6176', 1007, 'Merchandizer'),
(1046, 'Jennifer', 'Morgan', 'JenniferEMorgan@superrito.com', '606-239-8274', 1008, 'Merchandizer'),
(1047, 'Mildred', 'Cruz', 'MildredCCruz@gustr.com', '646-312-7255', 1002, 'Merchandizer'),
(1048, 'Judson', 'Lemieux', 'JudsonPLemieux@cuvox.de', '347-282-2746', 1002, 'Administrator'),
(1049, 'James', 'Watson', 'JamesVWatson@jourrapide.com', '336-389-2376', 1001, 'Merchandizer'),
(1050, 'Marilyn', 'Herbert', 'MarilynJHerbert@fleckens.hu', '650-997-9836', 1008, 'Administrator'),
(1051, 'Jennifer', 'Baker', 'JenniferHBaker@rhyta.com', '508-636-8386', 1005, 'Administrator'),
(1052, 'Lucy', 'Holland', 'LucyWHolland@cuvox.de', '716-740-5635', 1004, 'Assistant'),
(1053, 'Thomas', 'Cheney', 'ThomasLCheney@teleworm.us', '217-699-9422', 1001, 'Administrator'),
(1054, 'Jeffery', 'Gulick', 'JefferyBGulick@teleworm.us', '901-896-1930', 1003, 'Administrator'),
(1055, 'Daniel', 'Oviedo', 'DanielROviedo@cuvox.de', '972-834-8553', 1006, 'Administrator'),
(1056, 'Micah', 'Carroll', 'MicahKCarroll@rhyta.com', '313-336-0245', 1004, 'Merchandizer'),
(1057, 'Wilma', 'Keith', 'WilmaDKeith@dayrep.com', '740-801-9956', 1004, 'Administrator'),
(1058, 'Kathryn', 'Hagan', 'KathrynRHagan@fleckens.hu', '412-576-5464', 1006, 'Assistant'),
(1059, 'Lydia', 'Weeks', 'LydiaJWeeks@fleckens.hu', '407-529-6783', 1006, 'Assistant'),
(1060, 'Christine', 'Heal', 'ChristineDHeal@teleworm.us', '973-628-1650', 1001, 'Administrator'),
(1061, 'Clara', 'Reyes', 'ClaraMReyes@cuvox.de', '507-546-3456', 1002, 'Administrator'),
(1062, 'David', 'Sammons', 'DavidJSammons@cuvox.de', '740-477-6933', 1007, 'Administrator'),
(1063, 'James', 'Sheppard', 'JamesRSheppard@dayrep.com', '660-237-6174', 1006, 'Assistant'),
(1064, 'Randall', 'Everette', 'RandallDEverette@teleworm.us', '215-351-3376', 1008, 'Administrator'),
(1065, 'Kenneth', 'Carter', 'KennethDCarter@superrito.com', '701-273-7991', 1001, 'Merchandizer'),
(1066, 'Justin', 'Lewis', 'JustinMLewis@rhyta.com', '408-716-0115', 1002, 'Administrator'),
(1067, 'Martin', 'Christopherso', 'MartinBChristopherso@einrot.com', '510-339-5021', 1004, 'Assistant'),
(1068, 'Harvey', 'Wallace', 'HarveyLWallace@superrito.com', '401-296-6079', 1008, 'Administrator'),
(1069, 'Jessica', 'Lamar', 'JessicaSLamar@rhyta.com', '360-343-2494', 1008, 'Administrator'),
(1070, 'Jacqueline', 'Landon', 'JacquelineCLandon@cuvox.de', '804-520-2478', 1006, 'Assistant'),
(1071, 'Richard', 'Cahill', 'RichardCCahill@armyspy.com', '212-466-0360', 1003, 'Merchandizer'),
(1072, 'Michaela', 'McDaniel', 'MichaelaLMcDaniel@jourrapide.com', '412-977-6787', 1008, 'Merchandizer'),
(1073, 'Jo', 'Waller', 'JoWWaller@dayrep.com', '269-764-8453', 1001, 'Administrator'),
(1074, 'Joseph', 'Foster', 'JosephRFoster@superrito.com', '309-401-1671', 1008, 'Merchandizer'),
(1075, 'Megan', 'Crain', 'MeganSCrain@dayrep.com', '724-754-9027', 1008, 'Merchandizer'),
(1076, 'Sabrina', 'Lee', 'SabrinaLLee@gustr.com', '781-852-2235', 1001, 'Merchandizer'),
(1077, 'Tammy', 'Garza', 'TammyJGarza@jourrapide.com', '909-207-7621', 1003, 'Administrator'),
(1078, 'Elma', 'Pearsall', 'ElmaJPearsall@dayrep.com', '832-216-1606', 1004, 'Merchandizer'),
(1079, 'David', 'Henderson', 'DavidBHenderson@gustr.com', '405-483-7597', 1005, 'Administrator'),
(1080, 'Frances', 'Schweigert', 'FrancesCSchweigert@jourrapide.com', '765-737-8653', 1002, 'Assistant'),
(1081, 'Pedro', 'Ward', 'PedroAWard@gustr.com', '863-654-3623', 1008, 'Administrator'),
(1082, 'Timothy', 'Shockley', 'TimothyGShockley@teleworm.us', '773-393-1886', 1002, 'Merchandizer'),
(1083, 'Charles', 'Harvey', 'CharlesGHarvey@cuvox.de', '562-558-1822', 1001, 'Assistant'),
(1084, 'Lupe', 'Berg', 'LupeKBerg@rhyta.com', '215-594-4008', 1004, 'Assistant'),
(1085, 'Robert', 'Burket', 'RobertEBurket@einrot.com', '312-260-7299', 1007, 'Merchandizer'),
(1086, 'Georgia', 'Kappler', 'GeorgiaAKappler@teleworm.us', '214-915-9392', 1003, 'Assistant'),
(1087, 'Lakendra', 'McNamara', 'LakendraSMcNamara@gustr.com', '201-635-1187', 1008, 'Assistant'),
(1088, 'Marcus', 'Bueno', 'MarcusRBueno@teleworm.us', '509-453-3810', 1003, 'Administrator'),
(1089, 'Kathleen', 'Bermudez', 'KathleenSBermudez@fleckens.hu', '662-456-9177', 1008, 'Assistant'),
(1090, 'William', 'Gomes', 'WilliamJGomes@teleworm.us', '310-340-3696', 1003, 'Administrator'),
(1091, 'Jeffery', 'Chatham', 'JefferyIChatham@rhyta.com', '724-509-8772', 1002, 'Merchandizer'),
(1092, 'Felicia', 'Leach', 'FeliciaJLeach@armyspy.com', '216-462-8653', 1003, 'Assistant'),
(1093, 'Oscar', 'Dillman', 'OscarKDillman@cuvox.de', '858-395-9257', 1008, 'Administrator'),
(1094, 'Luis', 'Belle', 'LuisABelle@rhyta.com', '979-647-1546', 1002, 'Merchandizer'),
(1095, 'Michael', 'Couch', 'MichaelECouch@armyspy.com', '870-420-5207', 1002, 'Administrator'),
(1096, 'Roy', 'Edwards', 'RoyBEdwards@teleworm.us', '507-296-8452', 1004, 'Merchandizer'),
(1097, 'Patty', 'Hill', 'PattyDHill@jourrapide.com', '802-706-3987', 1008, 'Assistant'),
(1098, 'Margie', 'Decker', 'MargieTDecker@einrot.com', '323-432-1625', 1003, 'Administrator'),
(1099, 'Keith', 'Strode', 'KeithMStrode@superrito.com', '860-667-6906', 1008, 'Merchandizer'),
(1100, 'Leonard', 'King', 'LeonardMKing@dayrep.com', '562-705-5686', 1002, 'Merchandizer'),
(1101, 'Reva', 'Rogers', 'RevaWRogers@cuvox.de', '218-270-2866', 1008, 'Merchandizer'),
(1102, 'Deborah', 'Bechtold', 'DeborahEBechtold@cuvox.de', '321-205-7576', 1002, 'Administrator'),
(1103, 'Suzanne', 'Kern', 'SuzanneFKern@cuvox.de', '928-819-8335', 1002, 'Administrator'),
(1104, 'Michael', 'Cleveland', 'MichaelACleveland@teleworm.us', '318-396-4496', 1004, 'Merchandizer'),
(1105, 'Nicholas', 'Jones', 'NicholasMJones@rhyta.com', '360-901-4287', 1007, 'Administrator'),
(1106, 'Rachel', 'Lugo', 'RachelJLugo@armyspy.com', '703-329-0719', 1006, 'Administrator'),
(1107, 'Lori', 'Crabtree', 'LoriSCrabtree@einrot.com', '919-394-6025', 1002, 'Administrator'),
(1108, 'Jeff', 'Davis', 'JeffKDavis@superrito.com', '918-543-2503', 1008, 'Assistant'),
(1109, 'Richard', 'Maillet', 'RichardDMaillet@armyspy.com', '812-939-9345', 1002, 'Administrator'),
(1110, 'Andre', 'Addison', 'AndreBAddison@superrito.com', '410-933-1416', 1007, 'Merchandizer'),
(1111, 'Miguel', 'Jackson', 'MiguelDJackson@armyspy.com', '979-318-2072', 1003, 'Administrator'),
(1112, 'Lauren', 'Stone', 'LaurenRStone@teleworm.us', '703-614-6619', 1005, 'Merchandizer'),
(1113, 'Mary', 'Drummer', 'MaryFDrummer@cuvox.de', '864-410-3277', 1007, 'Administrator'),
(1114, 'John', 'Hartford', 'JohnEHartford@cuvox.de', '646-463-1769', 1007, 'Assistant'),
(1115, 'Jeremy', 'Spradlin', 'JeremyNSpradlin@fleckens.hu', '817-560-9106', 1008, 'Administrator'),
(1116, 'Catherine', 'Silva', 'CatherineHSilva@fleckens.hu', '765-292-5181', 1008, 'Merchandizer'),
(1117, 'Larry', 'Harris', 'LarryMHarris@dayrep.com', '916-687-6828', 1006, 'Merchandizer'),
(1118, 'Matt', 'Jones', 'MattJJones@superrito.com', '816-635-9835', 1008, 'Merchandizer'),
(1119, 'Sheila', 'Greiner', 'SheilaGGreiner@jourrapide.com', '217-414-1103', 1008, 'Assistant'),
(1120, 'Kathryn', 'Johnston', 'KathrynMJohnston@dayrep.com', '972-773-8927', 1007, 'Merchandizer')

