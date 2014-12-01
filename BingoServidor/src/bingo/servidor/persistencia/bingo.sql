/*
Start mysql on MACOS

sudo /usr/local/mysql/support-files/mysql.server start
 
sudo /usr/local/mysql/support-files/mysql.server stop

sudo /usr/local/mysql/support-files/mysql.server restart
 */ 

CREATE DATABASE bingo;


CREATE USER 'bingo'@'localhost' IDENTIFIED BY 'bingo';

GRANT ALL PRIVILEGES ON bingo.* TO 'bingo'@'localhost';

FLUSH PRIVILEGES;



USE bingo;

CREATE TABLE usuarios (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     usuario VARCHAR(30) NOT NULL,
     password VARCHAR(30) NOT NULL,
     saldo FLOAT,
     tipo INT,
     PRIMARY KEY (id)
);

CREATE TABLE config (
     clave varchar(30),
     valor varchar(30),
     PRIMARY KEY (clave)
);
 
INSERT INTO usuarios (usuario, password, saldo, tipo) VALUES
    ("mcarrero", "mcarrero", NULL, 1),
    ("fgonzalez", "fgonzalez", NULL, 1),
    ("j1", "j1", 1200, 2),
    ("j2", "j2", 2700, 2),
    ("j3", "j3", 4200, 2),
    ("j4", "j4", 7000, 2),
    ("j5", "j5", 30, 2);

INSERT INTO config VALUES
    ("CANT_FILAS", "2"),
    ("CANT_COLUMNAS", "2"),
    ("CANT_MAX_CARTONES", "2"),
    ("CANT_JUGADORES", "2"),
    ("VALOR_CARTON", "10.0"),
    ("FIGURA_CARTON_LLENO", "true"),
    ("FIGURA_DIAGONAL", "true"),
    ("FIGURA_LINEA", "true"),
    ("FIGURA_CENTRO", "true");
