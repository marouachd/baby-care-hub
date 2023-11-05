DROP TABLE IF EXISTS childs;
DROP TABLE IF EXISTS guard_mode;
DROP TABLE IF EXISTS genders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS persons;




CREATE TABLE genders (
    id SERIAL PRIMARY KEY,
    gender_name Varchar(100)
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

CREATE TABLE childs (
    id SERIAL PRIMARY KEY,
    birthday_date DATE ,
    gender_id INT ,
    guard_id INT,
    person_id INT,
    child_id Varchar(50),
    childminder_code Varchar(50),
    parent_id INT,
    FOREIGN KEY (gender_id) REFERENCES genders(id),
    FOREIGN KEY (guard_id) REFERENCES guard_mode(id),
    FOREIGN KEY (person_id) REFERENCES persons(id) 
);



