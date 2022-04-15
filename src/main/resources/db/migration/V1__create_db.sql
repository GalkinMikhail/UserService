drop table if exists users cascade;
drop table if exists roles cascade;

drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start 1 increment 1;

create table users
(
    id       bigserial not null,
    username varchar(255),
    password varchar(255),
    primary key (id)
);
alter table users
    add constraint uk_username unique (username);


create table roles
(
    id   bigserial not null,
    name varchar(255),
    primary key (id)
);
alter table roles
    add constraint uk_name unique (name);

create table user_roles
(
    user_id BIGINT not null,
    role_id BIGINT not null
);
alter table user_roles
    add constraint fkh8ciramu9cc9q3qcqiv4ue8a6 foreign key (role_id) references roles;
alter table user_roles
    add constraint fkhfh9dx7w3ubf1co1vdev94g3f foreign key (user_id) references users;
