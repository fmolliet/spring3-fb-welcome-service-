CREATE TABLE welcome_messages(
    id bigserial primary key,
    guild varchar(200) not null,
    content varchar(4000) not null,
    data timestamp not null
);