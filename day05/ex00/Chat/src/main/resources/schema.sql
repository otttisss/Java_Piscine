create schema if not exists chat;

create table if not exists chat_users (
    id          serial primary key,
    login       varchar(50) not null,
    password    varchar(50) not null
);

create table if not exists chat_rooms (
    id          serial primary key,
    room_name   text not null,
    owner       integer references chat_users(id) not null
);

create table if not exists chat_messages (
    id          serial primary key,
    author      integer references chat_users(id) not null,
    room        integer references chat_rooms(id) not null,
    timestamp   timestamp default CURRENT_TIMESTAMP
);
