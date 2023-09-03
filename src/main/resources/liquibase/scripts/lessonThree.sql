-- liquibase formatted sql

-- changeset uvolkov:1
create table lessonThree (
    id serial,
    name text
)

-- changeset uvolkov:2
create index names_index on lessonThree (name);

-- changeset uvolkov:2
create index nameAndColor on lessonTree