# --- !Ups

create table userdata
(
    id         bigint auto_increment
        primary key,
    email      varchar(100) null,
    password   varchar(100) null,
    full_name  varchar(100) null,
    role       varchar(100) null,
    created_at datetime     not null,
    updated_at datetime     null,
    constraint users_email_uindex
        unique (email)
);

# --- !Downs
DROP TABLE userdata;
