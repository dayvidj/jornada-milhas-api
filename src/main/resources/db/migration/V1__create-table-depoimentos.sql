create table depoimentos (
    id bigint auto_increment primary key,
    foto varchar(255) not null,
    texto text,
    nome varchar(255)
);