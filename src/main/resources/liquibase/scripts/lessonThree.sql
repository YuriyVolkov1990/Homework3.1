-- liquibase formatted sql

-- changeset uvolkov:1
create table lessonThree (
    id serial,
    name text
)

-- changeset uvolkov:2
create index names_index on student (name);

-- changeset uvolkov:3
create index nameAndColor_index on faculty (name, color);