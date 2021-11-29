create user lojadediscos with password 'pass123';

create table public.alb_album
(
    alb_id   bigserial,
    alb_nome varchar(255) not null,
    alb_ano  integer      not null,
    primary key (alb_id)
);

create table public.art_artista
(
    art_id   bigserial,
    art_nome varchar(255) not null,
    primary key (art_id)
);

create table public.aar_album_artista
(
    alb_id bigserial not null,
    art_id bigserial not null,
    primary key (alb_id, art_id),
    foreign key (alb_id) references alb_album on update cascade,
    foreign key (art_id) references art_artista on update cascade
);

create table public.fai_faixa
(
    fai_id      bigserial,
    fai_ordem   integer      not null,
    alb_id      bigserial    not null,
    fai_nome    varchar(255) not null,
    fai_duracao integer      not null,
    foreign key (alb_id) references alb_album on update cascade,
    primary key (fai_id)
);

create table public.usr_usuario
(
    usr_id    bigserial,
    usr_nome  varchar(20)  not null unique,
    usr_email varchar(100) not null unique,
    usr_senha varchar(100) not null,
    primary key (usr_id)
);

create table public.aut_autorizacao
(
    aut_id   bigserial,
    aut_nome varchar(20) not null unique,
    primary key (aut_id)
);

create table public.uau_usuario_autorizacao
(
    usr_id bigserial,
    aut_id bigserial,
    primary key (usr_id, aut_id),
    foreign key (usr_id) references usr_usuario on delete restrict on update cascade,
    foreign key (aut_id) references aut_autorizacao on delete restrict on update cascade
);

grant select, insert, delete, update on alb_album, art_artista, aar_album_artista, fai_faixa, usr_usuario, aut_autorizacao, uau_usuario_autorizacao to lojadediscos;
grant all privileges on sequence uau_usuario_autorizacao_usr_id_seq, uau_usuario_autorizacao_aut_id_seq, usr_usuario_usr_id_seq, aut_autorizacao_aut_id_seq, aar_album_artista_alb_id_seq, aar_album_artista_art_id_seq, alb_album_alb_id_seq, art_artista_art_id_seq, fai_faixa_alb_id_seq, fai_faixa_fai_id_seq to lojadediscos;

insert into aut_autorizacao (aut_nome)
values ('ROLE_ADMIN');
insert into aut_autorizacao (aut_nome)
values ('ROLE_USER');

insert into usr_usuario (usr_nome, usr_email, usr_senha)
values ('user', 'user@email.com', '$2y$10$h7.keYeH7Px3MJSp3lSg8evMk09n4HzmiOY/WihTJUCX6pn6mvTw6');
insert into usr_usuario (usr_nome, usr_email, usr_senha)
values ('admin', 'admin@email.com', '$2y$10$i2HtbqbyjnVe8tqcWybmG.gK57loCt2vik.9tctONKwqkZwaVvBE6');

insert into uau_usuario_autorizacao (aut_id, usr_id)
values (1, 2);
insert into uau_usuario_autorizacao (aut_id, usr_id)
values (2, 1);