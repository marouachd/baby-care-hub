DELETE FROM childs;
DELETE FROM guard_mode;
DELETE FROM genders;
DELETE FROM persons;
DELETE FROM roles;
DELETE FROM users;

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

					




	
	