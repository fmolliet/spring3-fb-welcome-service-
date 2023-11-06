CREATE TABLE usuarios(
    id bigserial primary key,
    login varchar(100) not null,
    senha varchar(255) not null,
    active boolean not null DEFAULT true
);