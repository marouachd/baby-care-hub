DELETE FROM actualities;
DELETE FROM childs_activities;
DELETE FROM naps;
DELETE FROM baby_bottles;
DELETE FROM meals;
DELETE FROM childs_leisures;
DELETE FROM snacks;
DELETE FROM activities;
DELETE FROM leisures;
DELETE FROM childs;
DELETE FROM users;
DELETE FROM persons;
DELETE FROM guard_mode;
DELETE FROM roles;
DELETE FROM genders;

-- Insertion des données dans les tables
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

INSERT INTO persons (pseudo_name, first_name, last_name, identity_photo_name) VALUES 
    ('mamanEyad', 'maroua', 'soltani', 'maroua.jpg'),
    ('bestmom', 'lamia', 'hajj', 'lamia.jpeg'),
    ('baby17', 'Eyad', 'chroud', '');

INSERT INTO users (mail_address, phone_number, password, role_id, person_id) VALUES
    ('nounou@gmail.com', '04785321', '251145hh', 1, 1),
    ('parent@gmail.com', '04785321', '251145hh', 2, 2);

INSERT INTO childs (
    birthday_date, gender_id, guard_id, person_id, childminder_code, parent_id, is_active, is_accepted, is_deleted
) VALUES (
    '2019-01-17', 1, 3, 3, 1, 2, true, true, false
);

INSERT INTO activities (activity_name) VALUES
    ('Lego'), 
    ('Puzzel'),
    ('Coloriage'),
    ('Dessin'),
    ('Peinture');

INSERT INTO leisures (leisure_name) VALUES
    ('Lire un livre'), 
    ('Danser'),
    ('Regarder les dessins animées'),
    ('au parc à coté'),
    ('à la bibliothèque'),
    ('au ludothèque');

INSERT INTO snacks (snack_name, image_path) VALUES
    ('Un fruit', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6ZnzI4gOfp-jmot0JloS1b3lZ8kC-e4sICQ&usqp=CAU'), 
    ('Un yaourt', 'https://media.istockphoto.com/id/1068916210/vector/yoghurt.jpg?s=612x612&w=0&k=20&c=KmSJ-a7Ol92ZCfVkuHu6c-Idy5XUoagt5mmVg1gEGjA='),
    ('une tartine', 'https://media.istockphoto.com/id/1128230134/vector/bread.jpg?s=612x612&w=0&k=20&c=xvAXIVc-Y9y8F5PQuRInN_378E-e3HaW1prIMrp-R38='),
    ('un gateaux', 'https://static9.depositphotos.com/1072356/1199/v/450/depositphotos_11997141-stock-illustration-chocolate-cookies.jpg');

-- Insertion des données dans les tables avec dépendances
INSERT INTO childs_activities (activity_id, child_id, commentaire, time, date) 
VALUES (1, 1, 'il a bien joué au puzzle', '12:20:00', '2024-03-28');

INSERT INTO naps (child_id, commentaire, start_time, end_time, type, date) VALUES
    (1, 'il a bien dormis', '12:00:00', '14:00:00', 'sieste', '2024-03-28');

INSERT INTO  baby_bottels (child_id, volume, time, date) VALUES
    (1, 250.0, '12:00:00', '2024-03-28');

INSERT INTO meals (child_id, snack_id, eval, commentaire, date, type)
VALUES (1, 2, 'très bien mangé', 'plat poisson + riz + fruit', '2024-03-28', 'gouter');

INSERT INTO childs_leisures (leisure_id, child_id, commentaire, date) 
VALUES (1, 1, 'il a bien joué', '2024-03-28');

INSERT INTO actualities (child_id, date, child_activity_id,baby_bottle_id, meal_id, nap_id, leisure_id)
VALUES (1, '2024-03-28', 1, 1, 1, 1, 1);