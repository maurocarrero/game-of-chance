CREATE DATABASE  IF NOT EXISTS `bingo`
USE `bingo`;

/*****CREACION DE TABLAS******/

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `logueado` BIT,
  CONSTRAINT PK_Usuario PRIMARY KEY (id)
)

DROP TABLE IF EXISTS `jugador`;
CREATE TABLE `jugador` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `cantCartones` INT,
  `saldo` DOUBLE,
  CONSTRAINT PK_Jugador PRIMARY KEY (id),
  CONSTRAINT FK_Usuario_Jugador FOREIGN KEY (idUsuario) REFERENCES usuario(id),
)

DROP TABLE IF EXISTS `carton`;
CREATE TABLE `carton` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `idJugador` INT NOT NULL,
  `cantCasilleros` INT,
  `cantAciertos` INT,
  `cantFilas` INT,
  `cantColumnas` INT,
  `saldo` DOUBLE,
  CONSTRAINT PK_Carton PRIMARY KEY (id),
  CONSTRAINT FK_Carton_Jugador FOREIGN KEY (idJugador) REFERENCES jugador(id),
)

DROP TABLE IF EXISTS `carton_numeros`;
CREATE TABLE `carton_numeros` (
  `idCarton` INT NOT NULL,
  `numero` INT,
  `pintado` BIT,
  CONSTRAINT PK_Carton_Numeros PRIMARY KEY (idCarton,numero),
  CONSTRAINT FK_Carton_Numeros_Carton FOREIGN KEY (idCarton) REFERENCES carton(id),
)

/*****INSERTS*******/
INSERT INTO `usuario` VALUES (1,'fgonzalez','fgonzalez','0');
INSERT INTO `usuario` VALUES (2,'mcarrero','mcarrero','0');
INSERT INTO `usuario` VALUES (3,'j1','j1','0');
INSERT INTO `usuario` VALUES (4,'j2','j2','0');
INSERT INTO `usuario` VALUES (5,'j3','j3','0');
INSERT INTO `usuario` VALUES (6,'j4','j4','0');
INSERT INTO `usuario` VALUES (7,'j5','j5','0');

INSERT INTO `jugador` VALUES (1,3,0,100);
INSERT INTO `jugador` VALUES (2,4,0,150);
INSERT INTO `jugador` VALUES (3,5,0,200);
INSERT INTO `jugador` VALUES (4,6,0,250);
INSERT INTO `jugador` VALUES (5,7,0,300);


