insert into chat.users(login, password)
values ('User1', '1234');

insert into chat.users(login, password)
values ('User2', '12345');

insert into chat.users(login, password)
values ('User3', '123456');

insert into chat.users(login, password)
values ('User4', '1234567');

insert into chat.users(login, password)
values ('User5', '12345678');

insert into chat.users(login, password)
values ('User6', '123456789');


insert into chat.rooms(room_name, owner)
values ('Chat1', 1);

insert into chat.rooms(room_name, owner)
values ('Chat2', 2);

insert into chat.rooms(room_name, owner)
values ('Chat3', 3);

insert into chat.rooms(room_name, owner)
values ('Chat4', 4);

insert into chat.rooms(room_name, owner)
values ('Chat5', 5);

insert into chat.rooms(room_name, owner)
values ('Chat6', 6);


insert into chat.messages(author, room, message)
values (1, 1, 'Hello');

insert into chat.messages(author, room, message)
values (2, 1, 'Badumtssss');

insert into chat.messages(author, room, message)
values (3, 1, 'How are u?');

insert into chat.messages(author, room, message)
values (1, 1, 'Wassup');

insert into chat.messages(author, room, message)
values (5, 3, 'Do not ask again');

insert into chat.messages(author, room, message)
values (6, 5, 'Hello there');
