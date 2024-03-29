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

-- DROP TABLE users;
--
-- CREATE TABLE users
-- (
--     username varchar(128) primary key ,
--     firstname varchar(128),
--     lastname varchar(128),
--     birth_date date,
--     role varchar(32),
--     info jsonb
-- );

-- DROP TABLE users;
--
-- CREATE TABLE users
-- (
--     id bigserial primary key , -- создается автоматически sequence
--     username varchar(128) unique ,
--     firstname varchar(128),
--     lastname varchar(128),
--     birth_date date,
--     role varchar(32),
--     info jsonb
-- );


-- DROP TABLE users;
--
-- CREATE TABLE users
-- (
--     id bigint primary key ,
--     username varchar(128) unique ,
--     firstname varchar(128),
--     lastname varchar(128),
--     birth_date date,
--     role varchar(32),
--     info jsonb
-- );
--
-- create sequence user_id_seq owned by users.id;



-- DROP TABLE users;
-- DROP TABLE company;
--
-- create table company
-- (
--     id serial primary key ,
--     name varchar(64) not null
-- );
--
-- CREATE TABLE users
-- (
--     id bigserial primary key ,
--     company_id int references company(id),
--     username varchar(128) unique ,
--     firstname varchar(128),
--     lastname varchar(128),
--     birth_date date,
--     role varchar(32),
--     info jsonb
-- );

DROP TABLE users;
DROP TABLE company;

create table company
(
    id serial primary key ,
    name varchar(64) not null
);

CREATE TABLE users
(
    id bigserial primary key ,
    company_id int references company(id),
    username varchar(128) unique ,
    firstname varchar(128),
    lastname varchar(128),
    birth_date date,
    role varchar(32),
    info jsonb
);

create table chat
(
    id bigserial primary key ,
    name varchar(64) unique NOT NULL
);

drop table if exists users_chat;

create table users_chat
(
    id bigserial primary key ,
    user_id bigint NOT NULL references users(id),
    chat_id bigint NOT NULL references chat(id),
    created_at timestamp not null ,
    created_by varchar(64) not null
);
