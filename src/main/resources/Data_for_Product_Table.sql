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