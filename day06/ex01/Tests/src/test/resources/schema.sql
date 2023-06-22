create schema if not exists shop;

create table shop.products (
    id integer,
    name varchar(20),
    price integer
)