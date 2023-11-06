create table consultas (
    id bigserial primary key,
    medico_id bigint not null,
    paciente_id bigint not null,
    data timestamp not null,
    
    foreign key (medico_id) references medicos(id),
    foreign key (paciente_id) references pacientes(id)
);