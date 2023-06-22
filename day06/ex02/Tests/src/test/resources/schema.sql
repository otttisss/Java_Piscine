create schema if not exists shop;
drop table if exists shop.products;

create table shop.products (
    id integer primary key identity,
    name varchar(100),
    price integer
);