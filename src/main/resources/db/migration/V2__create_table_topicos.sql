create table topicos(
    id bigint primary key not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(255) not null,
    fecha_creacion datetime not null,
    status tinyint not null,
    usuario_id bigint not null,
    curso varchar(255)
)