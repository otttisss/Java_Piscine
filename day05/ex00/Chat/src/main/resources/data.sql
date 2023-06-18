insert into chat_users(login, password)
values ('User1', '1234');

insert into chat_users(login, password)
values ('User2', '12345');

insert into chat_users(login, password)
values ('User3', '123456');

insert into chat_users(login, password)
values ('User4', '1234567');

insert into chat_users(login, password)
values ('User5', '12345678');

insert into chat_user(login, password)
values ('User6', '123456789');


insert into chat_rooms(room_name, owner)
values ('Chat1', 1);

insert into chat_rooms(room_name, owner)
values ('Chat2', 2);

insert into chat_rooms(room_name, owner)
values ('Chat3', 4);

insert into chat_rooms(room_name, owner)
values ('Chat4', 6);

insert into chat_rooms(room_name, owner)
values ('Chat5', 2);

insert into chat_room(room_name, owner)
values ('Chat6', 3);


insert into chat_messages(author, room, message)
values (1, 1, 'Hello');

insert into chat_messages(author, room, message)
values (2, 1, 'Badumtssss');

insert into chat_messages(author, room, message)
values (3, 1, 'How are u?');

insert into chat_messages(author, room, message)
values (1, 1, 'Wassup');

insert into chat_messages(author, room, message)
values (5, 3, 'Do not ask again');

insert into chat_messages(author, room, message)
values (6, 5, 'Hello there');
