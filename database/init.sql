-- CREATE TABLE users
-- (
--     username varchar(128) primary key ,
--     firstname varchar(128),
--     lastname varchar(128),
--     birth_date date,
--     age int
-- );

-- DROP TABLE users;
--
-- CREATE TABLE users
-- (
--     username varchar(128) primary key ,
--     firstname varchar(128),
--     lastname varchar(128),
--     birth_date date,
--     age int,
--     role varchar(32)
-- );

-- DROP TABLE users;
--
-- CREATE TABLE users
-- (
--     username varchar(128) primary key ,
--     firstname varchar(128),
--     lastname varchar(128),
--     birth_date date,
--     role varchar(32)
-- );

DROP TABLE users;

CREATE TABLE users
(
    username varchar(128) primary key ,
    firstname varchar(128),
    lastname varchar(128),
    birth_date date,
    role varchar(32),
    info jsonb
);