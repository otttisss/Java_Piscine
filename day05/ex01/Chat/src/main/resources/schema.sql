create schema if not exists chat;

create table if not exists chat.users (
    id          serial primary key,
    login       text not null,
    password    text not null
);

create table if not exists chat.rooms (
    id          serial primary key,
    room_name   text not null,
    owner       integer references chat.users(id) not null
);

create table if not exists chat.messages (
    id          serial primary key,
    author      integer references chat.users(id) not null,
    room        integer references chat.rooms(id) not null,
    message     text,
    timestamp   timestamp default CURRENT_TIMESTAMP
);
