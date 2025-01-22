create database encurtador_db;

use encurtador_db;

create table usuario(
		nome VARCHAR(128) NOT NULL,
		email VARCHAR(128) NOT NULL PRIMARY KEY,
	    senha VARCHAR(128) NOT NULL
);

insert into usuario (nome, email, senha) VALUES ("admin", "admin@gmail.com", "admin");

select * from usuario;