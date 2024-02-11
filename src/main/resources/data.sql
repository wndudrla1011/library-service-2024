insert into member (member_id, username, password, nickname, activated, email)
values (1, 'admin', '$2a$08$loDOSxczwXlrG90e31NkNOx9JquYMrwDixVdu2kLvVCbPDtt2SbZq', 'admin', 1, 'admin@gmail.com');

insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

insert into user_authority (member_id, authority_name) values (1, 'ROLE_USER');
insert into user_authority (member_id, authority_name) values (1, 'ROLE_ADMIN');