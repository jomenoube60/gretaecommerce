insert into restaurant (id , nom , adresse) values (1 , "Restaurant test" , "Rue Mireille Grenet , Compiègne");

insert into menu (id , nom , prixHT , actif , disponible )values (null , "Menu Végétarien" , 18.99 , 1 , 1),(null ,"Menu Enfant" , 18.99 , 1 , 1),(null , "Menu 20 euros" , 20.00 , 1 , 1),(null , "Menu Végétarien" , 18.99 , 1 , 1),(null , "Menu Végétarien" , 18.99 , 1 , 1);


insert into categorie (id , nom , restaurant_id , categorie_parente_id , image ,actif) values (null , "poisson" , 1 , null , null,1), (null , "viande" , 1 , null , null,1), (null , "dessert" , 1 , null , null,1), (null , "entrée" , 1 , null , null,1), (null , "boisson" , 1 , null , null,1);


insert into produit (id , nom , quantite_disponible, actif, prixHt) values (null , "Steak+Frites"  , 1, true, 10.99),(null , "Pates Bolognaises"  , 1, true, 9.99),(null , "Dame Blanche"  , 1, true, 9.99),(null , "Coca" , 1, true, 9.99),(null , "Poisson pané" , 1, true, 9.99);

insert into menu_produits (menu_id , produits_id) values (1,1),(1,2);

insert into produit_categories (produit_id , categories_id) values (1,2),(2,2),(3,3),(4,5),(5,1);
