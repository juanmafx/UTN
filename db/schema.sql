CREATE TABLE document_types (
    document_type_id INTEGER PRIMARY KEY,
    description VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE people (
    document_number INTEGER PRIMARY KEY CHECK (document_number > 0),
    document_type_id INTEGER NOT NULL REFERENCES document_types (document_type_id),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    height_meters NUMERIC(3, 2) CHECK (height_meters > 0 AND height_meters < 3)
);

CREATE TABLE students (
    file_number INTEGER PRIMARY KEY CHECK (file_number > 0),
    document_number INTEGER NOT NULL UNIQUE REFERENCES people (document_number),
    first_grade NUMERIC(4, 2) NOT NULL CHECK (first_grade BETWEEN 0 AND 10),
    second_grade NUMERIC(4, 2) NOT NULL CHECK (second_grade BETWEEN 0 AND 10),
    third_grade NUMERIC(4, 2) NOT NULL CHECK (third_grade BETWEEN 0 AND 10)
);

CREATE TABLE employees (
    file_number INTEGER PRIMARY KEY CHECK (file_number > 0),
    document_number INTEGER NOT NULL UNIQUE REFERENCES people (document_number),
    hours_worked NUMERIC(7, 2) NOT NULL CHECK (hours_worked >= 0),
    hourly_rate NUMERIC(10, 2) NOT NULL CHECK (hourly_rate >= 0)
);

CREATE TABLE library_members (
    member_number INTEGER PRIMARY KEY CHECK (member_number > 0),
    document_number INTEGER NOT NULL UNIQUE REFERENCES people (document_number)
);

CREATE TABLE bank_accounts (
    account_number INTEGER PRIMARY KEY CHECK (account_number >= 0),
    balance NUMERIC(14, 2) NOT NULL DEFAULT 0 CHECK (balance >= 0),
    type_code CHAR(1) NOT NULL CHECK (type_code IN ('I', 'T'))
);

CREATE TABLE brands (
    brand_id VARCHAR(5) PRIMARY KEY,
    description VARCHAR(100) NOT NULL
);

CREATE TABLE product_types (
    product_type_id INTEGER PRIMARY KEY,
    description VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE products (
    product_id INTEGER PRIMARY KEY,
    product_type_id INTEGER NOT NULL REFERENCES product_types (product_type_id),
    brand_id VARCHAR(5) NOT NULL REFERENCES brands (brand_id),
    name VARCHAR(100) NOT NULL,
    price NUMERIC(12, 2) NOT NULL CHECK (price >= 0),
    listed_on DATE NOT NULL
);

CREATE TABLE vehicles (
    vehicle_id INTEGER PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    kind VARCHAR(10) NOT NULL CHECK (kind IN ('PRIVATE', 'TAXI', 'HIRED_CAR')),
    model_year INTEGER NOT NULL CHECK (model_year >= 1886)
);

CREATE TABLE championships (
    championship_id INTEGER PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE teams (
    team_id INTEGER PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE match_days (
    match_day_id INTEGER PRIMARY KEY,
    championship_id INTEGER NOT NULL REFERENCES championships (championship_id),
    day_number INTEGER NOT NULL CHECK (day_number > 0),
    played_on DATE NOT NULL,
    UNIQUE (championship_id, day_number)
);

CREATE TABLE match_statuses (
    match_status_id INTEGER PRIMARY KEY,
    description VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE matches (
    match_id INTEGER PRIMARY KEY,
    match_day_id INTEGER NOT NULL REFERENCES match_days (match_day_id),
    match_status_id INTEGER NOT NULL REFERENCES match_statuses (match_status_id),
    home_team_id INTEGER NOT NULL REFERENCES teams (team_id),
    away_team_id INTEGER NOT NULL REFERENCES teams (team_id),
    home_goals INTEGER CHECK (home_goals >= 0),
    away_goals INTEGER CHECK (away_goals >= 0),
    CHECK (home_team_id <> away_team_id),
    UNIQUE (match_day_id, home_team_id, away_team_id)
);

CREATE INDEX idx_people_document_type ON people (document_type_id);
CREATE INDEX idx_products_type ON products (product_type_id);
CREATE INDEX idx_products_brand ON products (brand_id);
CREATE INDEX idx_match_days_played_on ON match_days (played_on);
CREATE INDEX idx_matches_status ON matches (match_status_id);
CREATE INDEX idx_matches_home_team ON matches (home_team_id);
CREATE INDEX idx_matches_away_team ON matches (away_team_id);
