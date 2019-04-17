 /* product table demo data */
insert into product (id, name, price, type, description, is_active, created_at, updated_at)

values
  /* sofas -----------> */
(1, 'Fava Sofa', 749.00, 'Sofa',
  'The Fava sofa offers up a perfect look and feel when you need a place to unwind.',
  true, null, null),

(2, 'Barcelona II Reclining Sofa', 799.00, 'Sofa',
  'The Barcelona II sofa offers the best of supportive comfort and classic style.',
  true, null, null),

(3, 'Caitlyn Sofa', 499.00, 'Sofa',
  'If you''ve been looking for a smaller-sized sofa that doesn''t sacrifice style or comfort, then your search stops here!',
  true, null, null),

(4, 'Devon Reclining Sofa', 999.00, 'Sofa',
  'Firm webbed seating provides long-lasting support, and the convenient size maximizes space for your versatile lifestyle.',
  true, null, null),

(5, 'Alabama Reclining Sofa', 899.00, 'Sofa',
  'Upholstered in a deep brown microfibre, the Alabama will add richness and a soft touch to your home, while retaining a sense of toughness.',
  true, null, null),
  /* <---------- sofas */

  /* chairs -----------> */
(6, 'Santos Accent Chair', 199.00, 'Chair',
  'The Santos accent chair features a chic, no-frills contemporary look with an armless design.',
  true, null, null),

(7, 'Crizia Chair', 699.00, 'Chair',
  'Contemporary yet comfortable style is on display with the Crizia chair, featuring clean lines and cozy charm.',
  true, null, null),

(8, 'Stella Accent Chair', 399.00, 'Chair',
  'The Stella accent chair exudes comfort and classic club chair style updated for the modern home.',
  true, null, null),

(9, 'Anthena Chair', 329.00, 'Chair',
  'With the Anthena chair in charcoal, you get superior comfort without sacrificing style.',
  true, null, null),

(10, 'Zia Accent Chair', 429.00, 'Chair',
  'The Zia accent chair boasts mid-century modern style for an on-trend retro look in your living area.',
  true, null, null);

 /* shop table demo data */
insert into shop (id, name, city, state, address, phone, created_at, updated_at)
values
(1, 'Echo Furniture', 'San Francisco', 'CA', '2222 21th St Noe Valley', '(444) 666-3344', null, null),

(2, 'Today’s Furniture', 'Portland', 'OR', '4244 Frank St', '(333) 667-2255', null, null),

(3, 'Furniture Envy', 'San Jose', 'CA', '300 Springs Rd', '(343) 988-2211', null, null),

(4, 'Room & Board', 'San Francisco ', 'CA', '925 E Richmond Ave', '(888) 909-3432', null, null),

(5, 'Furniture Innovation', 'Las Vegas', 'NV', '1108 Hattie St', '(415) 666-3344', null, null),

(6, 'Bedroom & More', 'Portland', 'OR', '1329 Magnolia Ave', '(415) 666-3344', null, null);

 /* shop_to_product table demo data */
insert into shop_to_product (shop_id, product_id, amount)
values
(1, 1, 50),
(1, 2, 50),
(1, 3, 50),
(1, 4, 50),
(1, 5, 50),
(1, 6, 100),
(1, 7, 100),
(1, 8, 100),
(1, 9, 100),
(1, 10, 100),

(2, 1, 30),
(2, 2, 30),
(2, 3, 30),
(2, 6, 50),
(2, 7, 50),
(2, 8, 50),
(2, 9, 50),
(2, 10, 50),

(3, 2, 20),
(3, 3, 20),
(3, 4, 20),
(3, 5, 20),
(3, 6, 100),
(3, 7, 100),
(3, 8, 50),
(3, 9, 50),
(3, 10, 100);