create table car (brand varchar not null, model varchar not null, price int8, id int primary key);
create table driver (name varchar unique not null, age int2 not null,haveLicence boolean, carId int references car (id));
