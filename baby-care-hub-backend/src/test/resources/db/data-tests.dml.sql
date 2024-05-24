DELETE FROM childs;
DELETE FROM guard_mode;
DELETE FROM genders;
DELETE FROM persons;
DELETE FROM roles;
DELETE FROM users;
DELETE FROM childs_activities;

INSERT INTO genders (gender_name) VALUES 
	('F'),
	('M');

INSERT INTO roles (role_name) VALUES 
	('Assistante maternelle'),
	('Parent');
	
INSERT INTO guard_mode (guard_mode_name) VALUES
	('Temps plein'),
	('Les mercredis'),
	('Les vacances scolaires'),
	('Sortie d''école');

INSERT INTO persons(pseudo_name,first_name,last_name,identity_photo_name) VALUES 
	('mamanEyad','maroua','soltani','maroua.jpg'),
	('bestmom','lamia','hajj','lamia.jpeg'),
	('baby17','Eyad','chroud','');
	
INSERT INTO users (mail_adress, phone_number,PASSWORD,role_id,person_id) VALUES
				('nounou@gmail.com','04785321','251145hh',1,2),
				 ('parent@gmail.com','04785321','251145hh',2,3);
				
INSERT INTO childs (birthday_date, child_id, gender_id, guard_id, person_id,childminder_code) VALUES 
                   ('2019-01-17', 'Eyad17', 2, 2, 3,1);
                  
INSERT INTO activities(activity_name) VALUES
						('Lego'), 
					    ('Puzzel'),
					   ('Coloriage'),
					     ('Dessin'),
					     ('Peinture');
					
INSERT INTO childs_activities (activity_id, child_id, commentaire, time) 
VALUES (1, 6, 'il a bien joué au puzzle', '12:20:00');

INSERT INTO naps(child_id,commentaire, start_time, end_time, type) VALUES
				(6,'il a bien dormis', '12:00:00', '14:00:00', 'sieste');
	

					
INSERT INTO baby_bottels(child_id,volume, time) VALUES
				(6,250.0, '12:00:00');
			
INSERT INTO meals(child_id, eval, commentaire, TYPE, snack_id)VALUES 
				(5, 'trés bien mangé', 'plat poisson + riz + fruit', 'gouter', 1);

INSERT INTO leisures(leisure_name) VALUES
						('Lire un livre'), 
					    ('Danser'),
					   ('Regarder les dessins animées'),
					     ('au parc à coté'),
					     ('à la biliothéque '),
					     ('au ludothèque');
INSERT INTO childs_leisures (leisure_id, child_id, commentaire) 
VALUES (1, 6, 'il a bien joué ');
	
INSERT INTO actualities (child_id, Date, child_activity_id, baby_bottel_id, meal_id, nap_id, leisure_id)
VALUES (5, '2024-03-28', 1, 6, 1, 1, 1);

INSERT INTO snacks(snack_name, image_path) VALUES
('Un fruit', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6ZnzI4gOfp-jmot0JloS1b3lZ8kC-e4sICQ&usqp=CAU'), 
('Un yaourt', 'https://media.istockphoto.com/id/1068916210/vector/yoghurt.jpg?s=612x612&w=0&k=20&c=KmSJ-a7Ol92ZCfVkuHu6c-Idy5XUoagt5mmVg1gEGjA='),
('une tartine', 'https://media.istockphoto.com/id/1128230134/vector/bread.jpg?s=612x612&w=0&k=20&c=xvAXIVc-Y9y8F5PQuRInN_378E-e3HaW1prIMrp-R38='),
('un gateaux', 'https://static9.depositphotos.com/1072356/1199/v/450/depositphotos_11997141-stock-illustration-chocolate-cookies.jpg');



	
	