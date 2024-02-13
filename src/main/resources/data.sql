insert into member (member_id, email, password, nickname, activated)
values (1, 'admin@gmail.com', '$2a$08$loDOSxczwXlrG90e31NkNOx9JquYMrwDixVdu2kLvVCbPDtt2SbZq', 'admin', 1);

insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

insert into member_authority (member_id, authority_name) values (1, 'ROLE_USER');
insert into member_authority (member_id, authority_name) values (1, 'ROLE_ADMIN');