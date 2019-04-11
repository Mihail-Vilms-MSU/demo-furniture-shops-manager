 /* product */
insert into product (name, price, description, is_active, created_at, updated_at)

values
('Big Mac', 3.99,
  'Two 100% beef patties, melted cheese, onions, pickles, lettuce and the signature Big Mac sauce make this an icon.',
  true, null, null),

('Big Tasty', 10.99,
  CONCAT('This legendary American burger is a true all-rounder! A 100% beef patty, Emmental cheese, onions, juicy tomatoes and ',
  'a unique smoky flavoured sauce all nested in a freshly toasted bun. Make it even better - add crispy bacon.'),
  true, null, null),

('McChicken', 1.29,
  'Our 100% chicken breast patty in a crispy coating, fresh lettuce and mayo sauce have made the McChicken an undisputed success since 1980.',
  true, null, null),

('Double Cheeseburger', 1.69,
  'Two 100% pure beef patties and two cheddar slices with ketchup, mustard, onions and pickles all nestled in our classic McDonald’s bun.',
  true, null, null),

('McDouble', 1.19,
  'A classic double burger from McDonald’s, the McDouble® stacks two 100% pure beef patties seasoned with just a pinch of salt and pepper.',
  true, null, null),

('Filet-O-Fish', 1.39,
  'Dive into our wild-caught fish sandwich! Our fish filet is made with Alaskan Pollock sourced from sustainable fisheries.',
  true, null, null),

('Sweet Tea', 1.49,
  'McDonald’s Sweet Tea is made from a briskly refreshing blend of orange pekoe and pekoe cut black tea, sweetened to perfection.',
  true, null, null),

('Chocolate Shake', 2.99,
  CONCAT('McDonald’s Chocolate Shake is a delicious chocolate dessert made with our creamy vanilla soft serve and chocolate syrup, topped ',
  'with whipped topping.'),
  true, null, null),

('Orange Juice', 2.09, '100% orange juice, packed with Vitamin C.', true, null, null);