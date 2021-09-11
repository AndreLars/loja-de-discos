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
    fai_ordem   integer        not null,
    alb_id      bigserial      not null,
    fai_nome    varchar(255)   not null,
    fai_duracao integer        not null,
    foreign key (alb_id) references alb_album on update cascade,
    primary key (fai_id)
);

grant select, insert, delete, update on alb_album, art_artista, aar_album_artista, fai_faixa to lojadediscos;
grant all privileges on sequence aar_album_artista_alb_id_seq, aar_album_artista_art_id_seq, alb_album_alb_id_seq, art_artista_art_id_seq, fai_faixa_alb_id_seq, fai_faixa_fai_id_seq to lojadediscos;
