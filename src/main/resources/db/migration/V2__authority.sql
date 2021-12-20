create table authorities (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_authorities (
  user_id               bigint not null,
  authority_id               int not null,
  primary key (user_id, authority_id),
  foreign key (user_id) references users (id),
  foreign key (authority_id) references authorities (id)
);

insert into authorities (name)
values
('TAKE_USER_INFO'), ('TAKE_USERS_INFO');

insert into users_authorities (user_id, authority_id)
values
(1, 1),
(2, 1),
(2, 2);