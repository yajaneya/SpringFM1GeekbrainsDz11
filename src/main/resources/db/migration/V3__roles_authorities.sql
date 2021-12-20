CREATE TABLE roles_authorities (
  role_id                    int not null,
  authority_id               int not null,
  primary key (role_id, authority_id),
  foreign key (role_id) references roles (id),
  foreign key (authority_id) references authorities (id)
);

insert into roles_authorities (role_id, authority_id)
values
(1, 1),
(2, 1),
(2, 2);

insert into authorities (name)
values
('TAKE_USER_PAGE');

delete from users_authorities;

insert into users_authorities (user_id, authority_id)
values
(1, 3);