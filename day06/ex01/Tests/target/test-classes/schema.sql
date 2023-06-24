create schema if not exists shop;

create table shop.products (
    id int primary key identity,
    name varchar(100) not null,
    price int not null
);