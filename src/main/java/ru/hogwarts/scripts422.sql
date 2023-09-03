create table car (brand varchar not null, model varchar not null, price int8, id text primary key);
create table driver (name varchar, age int2 not null, carId text references car (id));
