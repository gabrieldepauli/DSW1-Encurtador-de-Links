CREATE DATABASE encurtador_db;
USE encurtador_db;

CREATE TABLE usuario(
	nome VARCHAR(128) NOT NULL,
	email VARCHAR(128) NOT NULL PRIMARY KEY,
	senha VARCHAR(128) NOT NULL
);

CREATE TABLE link (
    id INT AUTO_INCREMENT PRIMARY KEY,
    url_original TEXT NOT NULL,
    url_encurtada VARCHAR(40) NOT NULL UNIQUE,
    email_criador VARCHAR(128),
    privado BOOLEAN NOT NULL DEFAULT(FALSE),
    FOREIGN KEY (email_criador) REFERENCES usuario(email)
);

CREATE TABLE acessos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    url_id INT NOT NULL,
    ip_cliente VARCHAR(100) NOT NULL,
    data_hora_acesso DATETIME NOT NULL,
    FOREIGN KEY (url_id) REFERENCES link(id) ON DELETE CASCADE
);

