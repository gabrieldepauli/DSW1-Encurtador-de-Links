create database encurtador_db;

use encurtador_db;

create table usuario(
	nome VARCHAR(128) NOT NULL,
	email VARCHAR(128) NOT NULL PRIMARY KEY,
	senha VARCHAR(128) NOT NULL
);

select * from usuario;

CREATE TABLE link (
    id INT AUTO_INCREMENT PRIMARY KEY,
    url_original TEXT NOT NULL,
    url_encurtada VARCHAR(30) NOT NULL UNIQUE,
    email_criador VARCHAR(128),
    FOREIGN KEY (email_criador) REFERENCES usuario(email)
);

select * from url;

CREATE TABLE acessos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    url_id INT NOT NULL,
    ip_cliente VARCHAR(100) NOT NULL,
    FOREIGN KEY (url_id) REFERENCES link(id)
);