-- Définition du schéma public si nécessaire
SET search_path TO public;

-- Suppression des tables avec CASCADE
DROP TABLE IF EXISTS actualities CASCADE;
DROP TABLE IF EXISTS baby_bottels CASCADE;
DROP TABLE IF EXISTS childs_activities CASCADE;
DROP TABLE IF EXISTS childs_leisures CASCADE;
DROP TABLE IF EXISTS histories CASCADE;
DROP TABLE IF EXISTS meals CASCADE;
DROP TABLE IF EXISTS naps CASCADE;
DROP TABLE IF EXISTS snacks CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS persons CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS genders CASCADE;
DROP TABLE IF EXISTS guard_mode CASCADE;
DROP TABLE IF EXISTS leisures CASCADE;
DROP TABLE IF EXISTS password_reset_token CASCADE;
DROP TABLE IF EXISTS childs;
DROP TABLE IF EXISTS activities;


-- Création des tables de base
CREATE TABLE genders (
    id SERIAL PRIMARY KEY,
    gender_name VARCHAR(100)
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    role_name VARCHAR(100)
);

CREATE TABLE guard_mode (
    id SERIAL PRIMARY KEY,
    guard_mode_name VARCHAR(100)
);

CREATE TABLE persons (
    id SERIAL PRIMARY KEY,
    pseudo_name VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    identity_photo_name VARCHAR(255)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    mail_adress VARCHAR(100),
    phone_number VARCHAR(100),
    password VARCHAR(100),
    role_id INT,
    person_id INT,
    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (person_id) REFERENCES persons(id)
);

CREATE TABLE histories (
    id SERIAL PRIMARY KEY,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    child_id INT,
    childminder_id INT
);

CREATE TABLE childs (
    id SERIAL PRIMARY KEY,
    birthday_date DATE,
    gender_id INT,
    guard_id INT,
    person_id INT,
    childminder_code INT,
    parent_id INT,
    is_active BOOLEAN,
    is_accepted BOOLEAN DEFAULT false,
    history_id INT,
    is_deleted BOOLEAN DEFAULT false,
    FOREIGN KEY (history_id) REFERENCES histories(id)
);

-- Création des autres tables
CREATE TABLE baby_bottels (
    id SERIAL PRIMARY KEY,
    child_id INT,
    time VARCHAR(100),
    date DATE,
    volume DOUBLE PRECISION,
    actuality_id INT
);

CREATE TABLE activities (
    id SERIAL PRIMARY KEY,
    activity_name VARCHAR(100)
);

CREATE TABLE childs_activities (
    id SERIAL PRIMARY KEY,
    activity_id INT,
    child_id INT,
    commentaire VARCHAR(300),
    time TIME,
    date DATE,
    FOREIGN KEY (activity_id) REFERENCES activities(id),
    FOREIGN KEY (child_id) REFERENCES childs(id)
);

CREATE TABLE snacks (
    id SERIAL PRIMARY KEY,
    snack_name VARCHAR(100),
    image_path VARCHAR(300)
);

CREATE TABLE meals (
    id SERIAL PRIMARY KEY,
    child_id INT,
    snack_id INT,
    eval VARCHAR(100),
    commentaire VARCHAR(300),
    date DATE,
    TYPE VARCHAR(100),
    FOREIGN KEY (child_id) REFERENCES childs(id),
    FOREIGN KEY (snack_id) REFERENCES snacks(id)
);

CREATE TABLE leisures (
    id SERIAL PRIMARY KEY,
    leisure_name VARCHAR(100)
);

CREATE TABLE childs_leisures (
    id SERIAL PRIMARY KEY,
    leisure_id INT,
    child_id INT,
    commentaire VARCHAR(300),
    date DATE,
    FOREIGN KEY (leisure_id) REFERENCES leisures(id),
    FOREIGN KEY (child_id) REFERENCES childs(id)
);

CREATE TABLE actualities (
    id SERIAL PRIMARY KEY,
    child_id INT,
    date DATE,
    child_activity_id INT,
    baby_bottel_id INT,
    meal_id INT,
    snack_id INT,
    nap_id INT,
    leisure_id INT,
    presence_id INT
);

CREATE TABLE naps (
    id SERIAL PRIMARY KEY,
    child_id INT,
    start_time VARCHAR(100),
    end_time VARCHAR(100),
    date DATE,
    TYPE VARCHAR(100),
    commentaire VARCHAR(300),
    actuality_id INT,
    FOREIGN KEY (child_id) REFERENCES childs(id),
    FOREIGN KEY (actuality_id) REFERENCES actualities(id)
);

CREATE TABLE password_reset_token (
    id SERIAL PRIMARY KEY,
    token VARCHAR(100),
    email VARCHAR(100),
    expiry_date TIMESTAMP
);

-- Ajout des contraintes de clés étrangères
ALTER TABLE baby_bottels
ADD CONSTRAINT fk_baby_bottel_child_id FOREIGN KEY (child_id) REFERENCES childs(id),
ADD CONSTRAINT fk_baby_bottel_actuality_id FOREIGN KEY (actuality_id) REFERENCES actualities(id);

ALTER TABLE childs_activities
ADD CONSTRAINT fk_childs_activities_activity_id FOREIGN KEY (activity_id) REFERENCES activities(id),
ADD CONSTRAINT fk_childs_activities_child_id FOREIGN KEY (child_id) REFERENCES childs(id);

ALTER TABLE meals
ADD CONSTRAINT fk_meals_child_id FOREIGN KEY (child_id) REFERENCES childs(id),
ADD CONSTRAINT fk_meals_snack_id FOREIGN KEY (snack_id) REFERENCES snacks(id);

ALTER TABLE childs_leisures
ADD CONSTRAINT fk_childs_leisures_leisure_id FOREIGN KEY (leisure_id) REFERENCES leisures(id),
ADD CONSTRAINT fk_childs_leisures_child_id FOREIGN KEY (child_id) REFERENCES childs(id);

ALTER TABLE actualities
ADD CONSTRAINT fk_actualities_child_id FOREIGN KEY (child_id) REFERENCES childs(id),
ADD CONSTRAINT fk_actualities_child_activity_id FOREIGN KEY (child_activity_id) REFERENCES childs_activities(id),
ADD CONSTRAINT fk_actualities_baby_bottel_id FOREIGN KEY (baby_bottel_id) REFERENCES baby_bottels(id),
ADD CONSTRAINT fk_actualities_meal_id FOREIGN KEY (meal_id) REFERENCES meals(id),
ADD CONSTRAINT fk_actualities_snack_id FOREIGN KEY (snack_id) REFERENCES snacks(id),
ADD CONSTRAINT fk_actualities_nap_id FOREIGN KEY (nap_id) REFERENCES naps(id),
ADD CONSTRAINT fk_actualities_leisure_id FOREIGN KEY (leisure_id) REFERENCES childs_leisures(id),
ADD CONSTRAINT fk_actualities_presence_id FOREIGN KEY (presence_id) REFERENCES naps(id);
