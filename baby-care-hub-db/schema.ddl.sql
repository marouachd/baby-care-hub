-- Drop tables if they exist
DROP TABLE IF EXISTS childs CASCADE;
DROP TABLE IF EXISTS guard_mode CASCADE;
DROP TABLE IF EXISTS genders CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS persons CASCADE;
DROP TABLE IF EXISTS activities CASCADE;
DROP TABLE IF EXISTS childs_activities CASCADE;
DROP TABLE IF EXISTS naps CASCADE;
DROP TABLE IF EXISTS baby_bottles CASCADE;
DROP TABLE IF EXISTS meals CASCADE ;
DROP TABLE IF EXISTS childs_leisures CASCADE;
DROP TABLE IF EXISTS actualities CASCADE;
DROP TABLE IF EXISTS snacks CASCADE;
DROP TABLE IF EXISTS password_reset_token;
DROP TABLE IF EXISTS histories CASCADE;
DROP TABLE IF EXISTS leisures CASCADE;
DROP TABLE IF EXISTS childs_leisures CASCADE;

-- Create tables
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
    mail_address VARCHAR(100),
    phone_number VARCHAR(100),
    password VARCHAR(100),
    role_id INT,
    person_id INT,
    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (person_id) REFERENCES persons(id)
);

CREATE TABLE activities (
    id SERIAL PRIMARY KEY,
    activity_name VARCHAR(100)
);

CREATE TABLE leisures (
    id SERIAL PRIMARY KEY,
    leisure_name VARCHAR(100)
);

CREATE TABLE snacks (
    id SERIAL PRIMARY KEY,
    snack_name VARCHAR(100),
    image_path VARCHAR(300)
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
    is_accepted BOOLEAN DEFAULT FALSE,
    history_id INT,
    is_deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (gender_id) REFERENCES genders(id),
    FOREIGN KEY (guard_id) REFERENCES guard_mode(id),
    FOREIGN KEY (person_id) REFERENCES persons(id),
    FOREIGN KEY (history_id) REFERENCES histories(id)
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

CREATE TABLE childs_leisures (
    id SERIAL PRIMARY KEY,
    leisure_id INT,
    child_id INT,
    commentaire VARCHAR(300),
    date DATE,
    FOREIGN KEY (leisure_id) REFERENCES leisures(id),
    FOREIGN KEY (child_id) REFERENCES childs(id)
);

CREATE TABLE meals (
    id SERIAL PRIMARY KEY,
    child_id INT,
    snack_id INT,
    eval VARCHAR(100),
    commentaire VARCHAR(300),
    date DATE,
    type VARCHAR(100),
    FOREIGN KEY (child_id) REFERENCES childs(id),
    FOREIGN KEY (snack_id) REFERENCES snacks(id)
);

CREATE TABLE baby_bottles (
    id SERIAL PRIMARY KEY,
    child_id INT,
    time VARCHAR(100),
    date DATE,
    volume DOUBLE PRECISION,
    actuality_id INT
);

CREATE TABLE naps (
    id SERIAL PRIMARY KEY,
    child_id INT,
    start_time VARCHAR(100),
    end_time VARCHAR(100),
    date DATE,
    type VARCHAR(100),
    commentaire VARCHAR(300),
    actuality_id INT,
    FOREIGN KEY (child_id) REFERENCES childs(id)
);

CREATE TABLE actualities (
    id SERIAL PRIMARY KEY,
    child_id INT,
    date DATE,
    child_activity_id INT,
    baby_bottle_id INT,
    meal_id INT,
    snack_id INT,
    nap_id INT,
    leisure_id INT,
    presence_id INT,
    FOREIGN KEY (child_id) REFERENCES childs(id),
    FOREIGN KEY (child_activity_id) REFERENCES childs_activities(id),
    FOREIGN KEY (baby_bottle_id) REFERENCES baby_bottles(id),
    FOREIGN KEY (meal_id) REFERENCES meals(id),
    FOREIGN KEY (snack_id) REFERENCES snacks(id),
    FOREIGN KEY (nap_id) REFERENCES naps(id),
    FOREIGN KEY (leisure_id) REFERENCES childs_leisures(id)
);

CREATE TABLE password_reset_token (
    id SERIAL PRIMARY KEY,
    token VARCHAR(100),
    email VARCHAR(100),
    expiry_date TIMESTAMP
);

-- Add foreign key constraints between histories and childs tables
ALTER TABLE histories
    ADD CONSTRAINT fk_childs_histories
    FOREIGN KEY (child_id) REFERENCES childs(id),
    ADD CONSTRAINT fk_childminder_histories
    FOREIGN KEY (childminder_id) REFERENCES persons(id);


