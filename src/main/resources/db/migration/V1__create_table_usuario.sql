create table usuarios(
    id bigint primary key not null auto_increment,
    username varchar(50) unique not null ,
    password varchar(255) not null
)