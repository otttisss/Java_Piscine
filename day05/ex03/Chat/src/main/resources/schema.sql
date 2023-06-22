drop table if exists chat.users, chat.rooms, chat.messages;
drop schema if exists chat;

create schema if not exists chat;

create table if not exists chat.users (
    id          serial primary key,
    login       text not null,
    password    text not null
);

create table if not exists chat.rooms (
    id          serial primary key,
    room_name   text not null,
    owner       int not null references chat.users(id)
);

create table if not exists chat.messages (
    id          serial primary key,
    author      int not null references chat.users(id),
    room        int not null references chat.rooms(id),
    message     text,
    timestamp   timestamp default CURRENT_TIMESTAMP
);
