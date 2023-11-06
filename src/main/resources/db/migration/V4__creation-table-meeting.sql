CREATE TABLE meeting(
    id bigserial primary key,
    name varchar(200) not null,
    snowflake varchar(50) not null unique,
    state varchar(2) not null,
    active boolean not null DEFAULT true
);