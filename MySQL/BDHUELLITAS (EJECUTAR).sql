-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema huellitas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema huellitas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `huellitas` DEFAULT CHARACTER SET utf8 ;
USE `huellitas` ;

-- -----------------------------------------------------
-- Table `huellitas`.`ESTADO_RESERVA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huellitas`.`ESTADO_RESERVA` (
  `cod_estadoreserva` INT NOT NULL AUTO_INCREMENT,
  `Estado` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`cod_estadoreserva`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `huellitas`.`METODO_PAGO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huellitas`.`METODO_PAGO` (
  `cod_metodopago` INT NOT NULL AUTO_INCREMENT,
  `Tipo_pago` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cod_metodopago`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `huellitas`.`RESERVA_PASEO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huellitas`.`RESERVA_PASEO` (
  `Nro_ticket` VARCHAR(20) NOT NULL,
  `fecha_r` DATE NOT NULL,
  `hora_r` TIME NOT NULL,
  `direccion_r` VARCHAR(150) NOT NULL,
  `tiempo_paseo` INT NOT NULL,
  `pagar_con` INT NULL,
  `precio` DECIMAL NOT NULL,
  `fh_res_gen` DATETIME NOT NULL,
  `cod_estadoreserva` INT NOT NULL,
  `cod_metodopago` INT NOT NULL,
  PRIMARY KEY (`Nro_ticket`),
  INDEX `fk_RESERVA_PASEO_ESTADO_R1_idx` (`cod_estadoreserva` ASC),
  INDEX `fk_RESERVA_PASEO_METODO_PAGO1_idx` (`cod_metodopago` ASC),
  CONSTRAINT `fk_RESERVA_PASEO_ESTADO_R1`
    FOREIGN KEY (`cod_estadoreserva`)
    REFERENCES `huellitas`.`ESTADO_RESERVA` (`cod_estadoreserva`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RESERVA_PASEO_METODO_PAGO1`
    FOREIGN KEY (`cod_metodopago`)
    REFERENCES `huellitas`.`METODO_PAGO` (`cod_metodopago`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `huellitas`.`ESTADO_RECLAMO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huellitas`.`ESTADO_RECLAMO` (
  `cod_estadoreclamo` INT NOT NULL AUTO_INCREMENT,
  `Nombre_estado` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`cod_estadoreclamo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `huellitas`.`TIPO_USUARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huellitas`.`TIPO_USUARIO` (
  `cod_tipousuario` INT NOT NULL AUTO_INCREMENT,
  `Rol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cod_tipousuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `huellitas`.`USUARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huellitas`.`USUARIO` (
  `cod_usuario` INT NOT NULL AUTO_INCREMENT,
  `usuario_u` VARCHAR(8) NOT NULL,
  `contrasena` VARCHAR(8) NOT NULL,
  `cod_tipousuario` INT NOT NULL,
  PRIMARY KEY (`cod_usuario`),
  INDEX `fk_USUARIO_TIPO_USUARIO1_idx` (`cod_tipousuario` ASC),
  CONSTRAINT `fk_USUARIO_TIPO_USUARIO1`
    FOREIGN KEY (`cod_tipousuario`)
    REFERENCES `huellitas`.`TIPO_USUARIO` (`cod_tipousuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `huellitas`.`RECLAMO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huellitas`.`RECLAMO` (
  `Nro_ticketReclamo` VARCHAR(20) NOT NULL,
  `asunto_rec` VARCHAR(50) NOT NULL,
  `detalle_rec` TEXT NOT NULL,
  `fh_recl_gen` DATETIME NOT NULL,
  `nro_ticket` VARCHAR(20) NOT NULL,
  `cod_estadoreclamo` INT NOT NULL,
  `cod_responsable` INT NOT NULL,
  `cod_afectado` INT NOT NULL,
  PRIMARY KEY (`Nro_ticketReclamo`),
  INDEX `fk_RECLAMO_RESERVA_PASEO1_idx` (`nro_ticket` ASC),
  INDEX `fk_RECLAMO_ESTADO_RECLAMO1_idx` (`cod_estadoreclamo` ASC),
  INDEX `fk_RECLAMO_USUARIO1_idx` (`cod_responsable` ASC),
  INDEX `fk_RECLAMO_USUARIO2_idx` (`cod_afectado` ASC),
  CONSTRAINT `fk_RECLAMO_RESERVA_PASEO1`
    FOREIGN KEY (`nro_ticket`)
    REFERENCES `huellitas`.`RESERVA_PASEO` (`Nro_ticket`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RECLAMO_ESTADO_RECLAMO1`
    FOREIGN KEY (`cod_estadoreclamo`)
    REFERENCES `huellitas`.`ESTADO_RECLAMO` (`cod_estadoreclamo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RECLAMO_USUARIO1`
    FOREIGN KEY (`cod_responsable`)
    REFERENCES `huellitas`.`USUARIO` (`cod_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RECLAMO_USUARIO2`
    FOREIGN KEY (`cod_afectado`)
    REFERENCES `huellitas`.`USUARIO` (`cod_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `huellitas`.`CLIENTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huellitas`.`CLIENTE` (
  `cod_cliente` INT NOT NULL AUTO_INCREMENT,
  `nomb_cli` VARCHAR(50) NOT NULL,
  `ape_cli` VARCHAR(50) NOT NULL,
  `direcc_cli` VARCHAR(100) NOT NULL,
  `cel_cli` INT NOT NULL,
  `fh_naci_cli` DATE NOT NULL,
  `correo_cli` VARCHAR(40) NOT NULL,
  `cod_usuario` INT NOT NULL,
  PRIMARY KEY (`cod_cliente`),
  INDEX `fk_CLIENTE_USUARIO1_idx` (`cod_usuario` ASC),
  CONSTRAINT `fk_CLIENTE_USUARIO1`
    FOREIGN KEY (`cod_usuario`)
    REFERENCES `huellitas`.`USUARIO` (`cod_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `huellitas`.`HISTORIAL_RESERVA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huellitas`.`HISTORIAL_RESERVA` (
  `cod_historial` INT NOT NULL AUTO_INCREMENT,
  `nro_ticket` VARCHAR(25) NOT NULL,
  `cod_usuario_cli` INT NOT NULL,
  `cod_usuario_petw` INT NULL,
  PRIMARY KEY (`cod_historial`),
  INDEX `fk_HISTORIAL_RESERVA_RESERVA_PASEO1_idx` (`nro_ticket` ASC),
  INDEX `fk_HISTORIAL_RESERVA_USUARIO1_idx` (`cod_usuario_cli` ASC),
  INDEX `fk_HISTORIAL_RESERVA_USUARIO2_idx` (`cod_usuario_petw` ASC),
  CONSTRAINT `fk_HISTORIAL_RESERVA_RESERVA_PASEO1`
    FOREIGN KEY (`nro_ticket`)
    REFERENCES `huellitas`.`RESERVA_PASEO` (`Nro_ticket`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HISTORIAL_RESERVA_USUARIO1`
    FOREIGN KEY (`cod_usuario_cli`)
    REFERENCES `huellitas`.`USUARIO` (`cod_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HISTORIAL_RESERVA_USUARIO2`
    FOREIGN KEY (`cod_usuario_petw`)
    REFERENCES `huellitas`.`USUARIO` (`cod_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `huellitas`.`PETWALKER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huellitas`.`PETWALKER` (
  `cod_petwalker` INT NOT NULL AUTO_INCREMENT,
  `nomb_petw` VARCHAR(50) NOT NULL,
  `ape_petw` VARCHAR(50) NOT NULL,
  `dni_petw` INT NOT NULL,
  `direcc_petw` VARCHAR(40) NOT NULL,
  `fh_naci_petw` DATE NOT NULL,
  `cel_petw` INT NOT NULL,
  `correo_petw` VARCHAR(40) NOT NULL,
  `cod_usuario` INT NOT NULL,
  PRIMARY KEY (`cod_petwalker`),
  INDEX `fk_PETWALKER_USUARIO1_idx` (`cod_usuario` ASC),
  CONSTRAINT `fk_PETWALKER_USUARIO1`
    FOREIGN KEY (`cod_usuario`)
    REFERENCES `huellitas`.`USUARIO` (`cod_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `huellitas`.`ADMINISTRADOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huellitas`.`ADMINISTRADOR` (
  `cod_admin` INT NOT NULL AUTO_INCREMENT,
  `nomb_adm` VARCHAR(50) NOT NULL,
  `ape_adm` VARCHAR(50) NOT NULL,
  `dni_adm` INT NOT NULL,
  `fh_naci_adm` DATE NOT NULL,
  `correo_adm` VARCHAR(40) NOT NULL,
  `cod_usuario` INT NOT NULL,
  PRIMARY KEY (`cod_admin`),
  INDEX `fk_ADMINISTRADOR_USUARIO1_idx` (`cod_usuario` ASC),
  CONSTRAINT `fk_ADMINISTRADOR_USUARIO1`
    FOREIGN KEY (`cod_usuario`)
    REFERENCES `huellitas`.`USUARIO` (`cod_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `huellitas`.`TARJETA_PAGO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huellitas`.`TARJETA_PAGO` (
  `cod_tarjeta` INT NOT NULL AUTO_INCREMENT,
  `nro_tarjeta` VARCHAR(4) NOT NULL,
  `Nombre_propietario` VARCHAR(45) NOT NULL,
  `Apellido_propietario` VARCHAR(45) NOT NULL,
  `cod_cliente` INT NOT NULL,
  PRIMARY KEY (`cod_tarjeta`),
  INDEX `fk_TARJETA_PAGO_CLIENTE1_idx` (`cod_cliente` ASC),
  CONSTRAINT `fk_TARJETA_PAGO_CLIENTE1`
    FOREIGN KEY (`cod_cliente`)
    REFERENCES `huellitas`.`CLIENTE` (`cod_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

Use huellitas;

/*--------------------TIPOS USUARIO------------------------*/
insert into tipo_usuario values (default,'Cliente');
insert into tipo_usuario values (default,'Petwalker');
insert into tipo_usuario values (default,'Administrador');

/*----------------------USUARIOS--------------------------*/

#CLIENTE
#------------------------------------
CREATE PROCEDURE INSERT_USER_CLIENTE(
  in c_usuario_u varchar(8),
  in c_contrasena varchar(8),
  in c_cod_tipousuario int,
  in c_nomb_cli varchar(50),
  in c_ape_cli varchar(50),
  in c_direcc_cli varchar(100),
  in c_cel_cli int,
  in c_fh_naci_cli date,
  in c_correo_cli varchar(40)
)

BEGIN
insert into usuario (usuario_u,contrasena,cod_tipousuario) values (c_usuario_u,c_contrasena,c_cod_tipousuario);
insert into cliente (cod_cliente,nomb_cli,ape_cli,direcc_cli,cel_cli,fh_naci_cli,correo_cli,cod_usuario) values (last_insert_id(),c_nomb_cli,c_ape_cli,c_direcc_cli,c_cel_cli,c_fh_naci_cli,c_correo_cli,last_insert_id());
end;

CALL INSERT_USER_CLIENTE ('jos123','123456',1,'Jose Raul','Jimenez Garcia','Jr. Noslai 125',951648512,'1994-05-23','jos@gmail.com');
CALL INSERT_USER_CLIENTE ('marta25','789456',1,'Marta Maria','Rosales Lara','Montanal 124',948154895,'1998-04-15','marta@gmail.com');

#ACTUALIZAR CLIENTE
#------------------------------------
CREATE PROCEDURE UPDATE_USER_CLIENTE(
  in c_cod_usuario int,
  in c_usuario_u varchar(8),
  in c_contrasena varchar(8),
  in c_cod_tipousuario int,
  in c_nomb_cli varchar(50),
  in c_ape_cli varchar(50),
  in c_direcc_cli varchar(100),
  in c_cel_cli int,
  in c_fh_naci_cli date,
  in c_correo_cli varchar(40)
)

BEGIN
update usuario set usuario_u = c_usuario_u,contrasena = c_contrasena,cod_tipousuario = c_cod_tipousuario where cod_usuario = c_cod_usuario;
update cliente set nomb_cli = c_nomb_cli,ape_cli = c_ape_cli,direcc_cli = c_direcc_cli,cel_cli = c_cel_cli,fh_naci_cli = c_fh_naci_cli,correo_cli = c_correo_cli where cod_usuario = c_cod_usuario;
end;

#ELIMINAR CLIENTE
#------------------------------------
CREATE PROCEDURE ELIMINAR_CLIENTE(
  in e_cod_cliente int
)

BEGIN
DELETE a1, a2 FROM
Cliente AS a1 INNER JOIN Usuario AS a2
WHERE a1.cod_usuario=a2.cod_usuario AND a1.cod_usuario LIKE e_cod_cliente;
end;

#PETWALKER
#--------------------------------------
CREATE PROCEDURE INSERT_USER_PETWALKER(
  in p_usuario_u varchar(8),
  in p_contrasena varchar(8),
  in p_cod_tipousuario int,
  in p_nomb_petw varchar(50),
  in p_ape_petw varchar(50),
  in p_dni_petw int,
  in p_direcc_petw varchar(40),
  in p_fh_naci_petw date,
  in p_cel_petw int,
  in p_correo_petw varchar(40)
)

BEGIN
insert into usuario (usuario_u,contrasena,cod_tipousuario) values (p_usuario_u,p_contrasena,p_cod_tipousuario);
insert into petwalker (nomb_petw,ape_petw,dni_petw,direcc_petw,fh_naci_petw,cel_petw,correo_petw,cod_usuario) values (p_nomb_petw,p_ape_petw,p_dni_petw,p_direcc_petw,p_fh_naci_petw,p_cel_petw,p_correo_petw,last_insert_id());
end;

CALL INSERT_USER_PETWALKER ('rau12','123456',2,'Raul','Oyola Sanchez',74815648,'Movidi 451','1994-07-15',941528495,'raul@hotmail.com');
CALL INSERT_USER_PETWALKER ('rosa48','987654',2,'Rosa Claudia','Julians Ramirez',74152684,'Rosgel 211','1999-09-29',915497895,'rosa@hotmail.com');

#ACTUALIZAR PETWALKER
#------------------------------------
CREATE PROCEDURE UPDATE_USER_PETWALKER(
  in p_cod_usuario int,
  in p_usuario_u varchar(8),
  in p_contrasena varchar(8),
  in p_cod_tipousuario int,
  in p_nomb_petw varchar(50),
  in p_ape_petw varchar(50),
  in p_dni_petw int,
  in p_direcc_petw varchar(40),
  in p_fh_naci_petw date,
  in p_cel_petw int,
  in p_correo_petw varchar(40)
)

BEGIN
UPDATE usuario set usuario_u=p_usuario_u,contrasena=p_contrasena,cod_tipousuario=p_cod_tipousuario where cod_usuario=p_cod_usuario;
UPDATE petwalker set nomb_petw=p_nomb_petw,ape_petw=p_ape_petw,dni_petw=p_dni_petw,direcc_petw=p_direcc_petw,fh_naci_petw=p_fh_naci_petw,cel_petw=p_cel_petw,correo_petw=p_correo_petw where cod_usuario = p_cod_usuario;
end;

#ELIMINAR PETWALKER
#------------------------------------
CREATE PROCEDURE ELIMINAR_PETWALKER(
  in e_cod_petw int
)

BEGIN
DELETE a1, a2 FROM
Petwalker AS a1 INNER JOIN Usuario AS a2
WHERE a1.cod_usuario=a2.cod_usuario AND a1.cod_usuario LIKE e_cod_petw;
end;

#ADMINISTRADOR
#------------------------------------------
CREATE PROCEDURE INSERT_USER_ADMINISTRADOR(
  in a_usuario_u varchar(8),
  in a_contrasena varchar(8),
  in a_cod_tipousuario int,
  in a_nomb_adm varchar(50),
  in a_ape_adm varchar(50),
  in a_dni_adm int,
  in a_fh_naci_adm date,
  in a_correo_adm varchar(40)
)

BEGIN
insert into usuario (usuario_u,contrasena,cod_tipousuario) values (a_usuario_u,a_contrasena,a_cod_tipousuario);
insert into administrador (nomb_adm,ape_adm,dni_adm,fh_naci_adm,correo_adm,cod_usuario) values (a_nomb_adm,a_ape_adm,a_dni_adm,a_fh_naci_adm,a_correo_adm,last_insert_id());
end;

CALL INSERT_USER_ADMINISTRADOR ('ful12','123456',3,'Fulanito','Ortega',14952847,'1984-04-13','fulanito@hotmail.com');


/*---------------------ESTADO RESERVA-----------------------*/
insert into estado_reserva values (default,'Pendiente');
insert into estado_reserva values (default,'En curso');
insert into estado_reserva values (default,'Finalizado');

/*---------------------METODO PAGO-------------------------*/
insert into metodo_pago values (default,'Efectivo');
insert into metodo_pago values (default,'Tarjeta');

/*-----------------------RESERVA---------------------------*/

#HISTORIAL RESERVA
#------------------------------------
CREATE PROCEDURE REGISTRO_RESERVA_HR(
    in hr_nro_ticket varchar(20),
    in hr_fecha_r date,
    in hr_hora_r time,
    in hr_direccion_r varchar(150),
    in hr_tiempo_pase int,
    in hr_pagar_con int,
    in hr_precio decimal,
    in hr_fh_res_gen datetime,
    in hr_cod_esadoreserva int,
    in hr_cod_metodopago int,
    in hr_cod_usuario_cli int,
    in hr_cod_usuario_petw int
)

BEGIN
insert into reserva_paseo values (hr_nro_ticket,hr_fecha_r,hr_hora_r,hr_direccion_r,hr_tiempo_pase,hr_pagar_con,hr_precio,hr_fh_res_gen,hr_cod_esadoreserva,hr_cod_metodopago);
insert into historial_reserva (nro_ticket,cod_usuario_cli,cod_usuario_petw) values(hr_nro_ticket,hr_cod_usuario_cli,hr_cod_usuario_petw);
end;


CALL REGISTRO_RESERVA_HR (CONCAT(YEAR(now()),MONTH(now()),DAY(now()),HOUR(now()),MINUTE(now()),SECOND(now())), '2021-05-29','15:30','Av. Manco Capac 150',3,0,45,now(),1,2,1,null);

#ACTUALIZAR RESERVA
#------------------------------------
CREATE PROCEDURE UPDATE_RESERVA(
    in hr_nro_ticket varchar(20),
    in hr_fecha_r date,
    in hr_hora_r time,
    in hr_direccion_r varchar(150),
    in hr_tiempo_paseo int,
    in hr_pagar_con int,
    in hr_precio decimal,
    in hr_cod_estadoreserva int,
    in hr_cod_metodopago int
)

BEGIN
update reserva_paseo set fecha_r=hr_fecha_r,hora_r=hr_hora_r,direccion_r=hr_direccion_r,tiempo_paseo=hr_tiempo_paseo,pagar_con=hr_pagar_con,precio=hr_precio,cod_estadoreserva=hr_cod_estadoreserva,cod_metodopago=hr_cod_metodopago where nro_ticket=hr_nro_ticket;
end;


#REALIZAR RESERVA
#------------------------------------

CREATE PROCEDURE REALIZAR_RESERVA(
    in re_nro_ticket varchar (20),
    in re_cod_usuario_petw int
)

BEGIN
UPDATE reserva_paseo set cod_estadoreserva = 2 where nro_ticket = re_nro_ticket;
UPDATE historial_reserva set cod_usuario_petw = re_cod_usuario_petw where nro_ticket = re_nro_ticket;
end;

#FINALIZAR RESERVA
#------------------------------------

CREATE PROCEDURE FINALIZAR_RESERVA(
    in re_nro_ticket varchar (20)
)

BEGIN
UPDATE reserva_paseo set cod_estadoreserva = 3 where nro_ticket = re_nro_ticket;
end;


/*-----------------------RECLAMO---------------------------*/

#ESTADOS RECLAMO
#------------------------------------
insert into estado_reclamo values (default,'Pendiente');
insert into estado_reclamo values (default,'En proceso');
insert into estado_reclamo values (default,'Finalizado');

#REGISTRAR RECLAMO
#------------------------------------
CREATE PROCEDURE REGISTRO_RECLAMO(
      in rr_asunto_rec varchar(50),
      in rr_detalle_rec text,
      in rr_nro_ticket varchar(20),
      in rr_cod_responsable int,
      in rr_cod_afectado int
)
BEGIN
insert into reclamo values (CONCAT(YEAR(now()),MONTH(now()),DAY(now()),HOUR(now()),MINUTE(now()),SECOND(now())),rr_asunto_rec,rr_detalle_rec,now(),rr_nro_ticket, 1, rr_cod_responsable, rr_cod_afectado);
end;

#ACTUALIZAR RECLAMO
#------------------------------------
CREATE PROCEDURE UPDATE_RECLAMO(
      in rr_nro_ticketreclamo varchar(20),
      in rr_asunto_rec varchar(50),
      in rr_detalle_rec text,
      in rr_cod_estadoreclamo int
)
BEGIN
update reclamo set asunto_rec=rr_asunto_rec,detalle_rec=rr_detalle_rec,cod_estadoreclamo=rr_cod_estadoreclamo where Nro_ticketReclamo=rr_nro_ticketreclamo;
end;

#--------------------------CONSULTAS/VISTAS-------------------------#
#PERFIL CLIENTE
#------------------------------------------
CREATE VIEW PERFIL_CLIENTE as
select u.cod_usuario,u.usuario_u,u.contrasena,c.nomb_cli,c.ape_cli,c.direcc_cli,c.cel_cli,c.fh_naci_cli,c.correo_cli,t.rol as TIPO from usuario u inner join tipo_usuario t 
on u.cod_tipousuario = t.cod_tipousuario inner join cliente c 
on u.cod_usuario = c.cod_usuario; 

CREATE VIEW PERFIL_CLIENTE2 as
select u.cod_usuario,u.usuario_u,u.contrasena,c.nomb_cli,c.ape_cli,c.direcc_cli,c.cel_cli,c.fh_naci_cli,c.correo_cli,t.rol as TIPO from usuario u inner join tipo_usuario t 
on u.cod_tipousuario = t.cod_tipousuario inner join cliente c 
on u.cod_usuario = c.cod_usuario where t.rol = 'Cliente';

#PERFIL PETWALKER
#------------------------------------------
CREATE VIEW PERFIL_PETWALKER as
select u.cod_usuario,u.usuario_u,u.contrasena,p.nomb_petw,p.ape_petw,p.direcc_petw,p.cel_petw,p.fh_naci_petw,p.correo_petw,t.rol as TIPO from usuario u inner join tipo_usuario t 
on u.cod_tipousuario = t.cod_tipousuario inner join petwalker p 
on u.cod_usuario = p.cod_usuario;

CREATE VIEW PERFIL_PETWALKER2 as
select u.cod_usuario,u.usuario_u,u.contrasena,p.nomb_petw,p.ape_petw,p.dni_petw,p.direcc_petw,p.cel_petw,p.fh_naci_petw,p.correo_petw,t.rol as TIPO from usuario u inner join tipo_usuario t 
on u.cod_tipousuario = t.cod_tipousuario inner join petwalker p 
on u.cod_usuario = p.cod_usuario where t.rol = 'Petwalker';

#PERFIL ADMINISTRADOR
#------------------------------------------
CREATE VIEW PERFIL_ADMINISTRADOR as
select u.cod_usuario,u.usuario_u,u.contrasena,a.nomb_adm,a.ape_adm,a.fh_naci_adm,a.correo_adm,t.rol as TIPO from usuario u inner join tipo_usuario t 
on u.cod_tipousuario = t.cod_tipousuario inner join administrador a 
on u.cod_usuario = a.cod_usuario;

#LISTADO DE RESERVA
#------------------------------------------
CREATE VIEW LISTADO_RESERVAS as
select r.nro_ticket,r.fecha_r,r.hora_r,r.direccion_r,r.tiempo_paseo,r.pagar_con,r.precio,r.fh_res_gen,er.Estado,mp.Tipo_pago,hr.cod_usuario_cli,CONCAT(c.nomb_cli,' ',c.ape_cli) as Cliente,if(hr.cod_usuario_petw IS NULL,0,hr.cod_usuario_petw) as cod_usuario_petw,if(hr.cod_usuario_petw IS NULL,'En espera',CONCAT(p.nomb_petw,' ',p.ape_petw)) as Petwalker 
from historial_reserva hr left join Cliente c
on hr.cod_usuario_cli = c.cod_usuario left join Petwalker p
on hr.cod_usuario_petw = p.cod_usuario inner join reserva_paseo r
on r.nro_ticket = hr.nro_ticket  inner join estado_reserva er
on r.cod_estadoreserva = er.cod_estadoreserva inner join metodo_pago mp
on r.cod_metodopago = mp.cod_metodopago;

#ESTADOS DE RESERVA
#------------------------------------------
CREATE VIEW PASEOS_PENDIENTES as
select r.nro_ticket,r.fecha_r,r.hora_r,r.direccion_r,r.tiempo_paseo,r.pagar_con,r.precio,r.fh_res_gen,er.Estado,mp.Tipo_pago,hr.cod_usuario_cli,CONCAT(c.nomb_cli,' ',c.ape_cli) as Cliente,if(hr.cod_usuario_petw IS NULL,0,hr.cod_usuario_petw) as cod_usuario_petw,if(hr.cod_usuario_petw IS NULL,'En espera',CONCAT(p.nomb_petw,' ',p.ape_petw)) as Petwalker 
from historial_reserva hr left join Cliente c
on hr.cod_usuario_cli = c.cod_usuario left join Petwalker p
on hr.cod_usuario_petw = p.cod_usuario inner join reserva_paseo r
on r.nro_ticket = hr.nro_ticket  inner join estado_reserva er
on r.cod_estadoreserva = er.cod_estadoreserva inner join metodo_pago mp
on r.cod_metodopago = mp.cod_metodopago where Estado='Pendiente' || Estado='En curso'
order by r.fh_res_gen;
#------------------------------------------
CREATE VIEW RESERVA_PENDIENTE as
select r.nro_ticket,r.fecha_r,r.hora_r,r.direccion_r,r.tiempo_paseo,r.pagar_con,r.precio,r.fh_res_gen,er.Estado,mp.Tipo_pago,hr.cod_usuario_cli,CONCAT(c.nomb_cli,' ',c.ape_cli) as Cliente,hr.cod_usuario_petw,CONCAT(p.nomb_petw,' ',p.ape_petw) as Petwalker 
from historial_reserva hr left join Cliente c
on hr.cod_usuario_cli = c.cod_usuario left join Petwalker p
on hr.cod_usuario_petw = p.cod_usuario inner join reserva_paseo r
on r.nro_ticket = hr.nro_ticket  inner join estado_reserva er
on r.cod_estadoreserva = er.cod_estadoreserva inner join metodo_pago mp
on r.cod_metodopago = mp.cod_metodopago where Estado='Pendiente' 
order by r.fh_res_gen;
#------------------------------------------
CREATE VIEW RESERVA_ENCURSO as
select r.nro_ticket,r.fecha_r,r.hora_r,r.direccion_r,r.tiempo_paseo,r.pagar_con,r.precio,r.fh_res_gen,er.Estado,mp.Tipo_pago,hr.cod_usuario_cli,CONCAT(c.nomb_cli,' ',c.ape_cli) as Cliente,hr.cod_usuario_petw,CONCAT(p.nomb_petw,' ',p.ape_petw) as Petwalker 
from historial_reserva hr inner join Cliente c
on hr.cod_usuario_cli = c.cod_usuario inner join Petwalker p
on hr.cod_usuario_petw = p.cod_usuario inner join reserva_paseo r
on r.nro_ticket = hr.nro_ticket  inner join estado_reserva er
on r.cod_estadoreserva = er.cod_estadoreserva inner join metodo_pago mp
on r.cod_metodopago = mp.cod_metodopago where Estado='En curso'
order by r.fh_res_gen;
#------------------------------------------
CREATE VIEW RESERVA_FINALIZADO as
select r.nro_ticket,r.fecha_r,r.hora_r,r.direccion_r,r.tiempo_paseo,r.pagar_con,r.precio,r.fh_res_gen,er.Estado,mp.Tipo_pago,hr.cod_usuario_cli,CONCAT(c.nomb_cli,' ',c.ape_cli) as Cliente,hr.cod_usuario_petw,CONCAT(p.nomb_petw,' ',p.ape_petw) as Petwalker 
from historial_reserva hr inner join Cliente c
on hr.cod_usuario_cli = c.cod_usuario inner join Petwalker p
on hr.cod_usuario_petw = p.cod_usuario inner join reserva_paseo r
on r.nro_ticket = hr.nro_ticket  inner join estado_reserva er
on r.cod_estadoreserva = er.cod_estadoreserva inner join metodo_pago mp
on r.cod_metodopago = mp.cod_metodopago where Estado='Finalizado'
order by r.fh_res_gen;
#------------------------------------------
CREATE VIEW DETALLE_RESERVA as
select r.nro_ticket,r.fecha_r,r.hora_r,r.direccion_r,r.tiempo_paseo,r.pagar_con,r.precio,r.fh_res_gen,er.Estado,mp.Tipo_pago,hr.cod_usuario_cli,CONCAT(c.nomb_cli,' ',c.ape_cli) as Cliente,hr.cod_usuario_petw,CONCAT(p.nomb_petw,' ',p.ape_petw) as Petwalker 
from historial_reserva hr inner join Cliente c
on hr.cod_usuario_cli = c.cod_usuario inner join Petwalker p
on hr.cod_usuario_petw = p.cod_usuario inner join reserva_paseo r
on r.nro_ticket = hr.nro_ticket  inner join estado_reserva er
on r.cod_estadoreserva = er.cod_estadoreserva inner join metodo_pago mp
on r.cod_metodopago = mp.cod_metodopago
order by r.fh_res_gen;

#LISTADO DE RECLAMOS - PETWALKER
#------------------------------------------
CREATE VIEW LISTADO_RECLAMO_PETW as
select r.Nro_ticketReclamo,r.asunto_rec,r.detalle_rec,r.fh_recl_gen,r.nro_ticket,er.nombre_estado as Estado,CONCAT(c.nomb_cli,' ',c.ape_cli) as Responsable,cod_responsable,cod_afectado
from reclamo r inner join reserva_paseo rp
on r.nro_ticket = rp.nro_ticket inner join estado_reclamo er
on r.cod_estadoreclamo = er.cod_estadoreclamo inner join historial_reserva hr
on rp.nro_ticket = hr.nro_ticket inner join Cliente c
on hr.cod_usuario_cli = c.cod_usuario inner join Petwalker p
on hr.cod_usuario_petw = p.cod_usuario;

#LISTADO DE RECLAMOS - CLIENTE
#------------------------------------------
CREATE VIEW LISTADO_RECLAMO_CLI as
select r.Nro_ticketReclamo,r.asunto_rec,r.detalle_rec,r.fh_recl_gen,r.nro_ticket,er.nombre_estado as Estado,CONCAT(p.nomb_petw,' ',p.ape_petw) as Responsable,cod_responsable,cod_afectado
from reclamo r inner join reserva_paseo rp
on r.nro_ticket = rp.nro_ticket inner join estado_reclamo er
on r.cod_estadoreclamo = er.cod_estadoreclamo inner join historial_reserva hr
on rp.nro_ticket = hr.nro_ticket inner join Cliente c
on hr.cod_usuario_cli = c.cod_usuario inner join Petwalker p
on hr.cod_usuario_petw = p.cod_usuario;

#LISTADO DE RECLAMOS
#------------------------------------------
CREATE VIEW LISTADO_RECLAMO as
select r.Nro_ticketReclamo,r.asunto_rec,r.detalle_rec,r.fh_recl_gen,r.nro_ticket,er.nombre_estado as Estado,IF(r.cod_responsable = c.cod_usuario,CONCAT(c.nomb_cli,' ',c.ape_cli),CONCAT(p.nomb_petw,' ',p.ape_petw)) as Responsable,IF(r.cod_afectado = c.cod_usuario,CONCAT(c.nomb_cli,' ',c.ape_cli),CONCAT(p.nomb_petw,' ',p.ape_petw)) as Afectado,r.cod_responsable,r.cod_afectado
from reclamo r inner join reserva_paseo rp
on r.nro_ticket = rp.nro_ticket inner join estado_reclamo er
on r.cod_estadoreclamo = er.cod_estadoreclamo inner join historial_reserva hr
on rp.nro_ticket = hr.nro_ticket inner join Cliente c
on hr.cod_usuario_cli = c.cod_usuario inner join Petwalker p
on hr.cod_usuario_petw = p.cod_usuario;


#DASHBOARD USUARIOS
#------------------------------------------
CREATE VIEW CANTIDAD_USUARIO as
select t.Rol,COUNT(u.cod_tipousuario) as Cantidad from usuario u inner join tipo_usuario t 
on u.cod_tipousuario=t.cod_tipousuario group by t.Rol;


CREATE VIEW CANTIDAD_RECLAMOS_PC as
select IF(r.cod_afectado = c.cod_usuario,'CLIENTE','PETWALKER') as AFECTADO, COUNT(r.cod_afectado) as CANTIDAD from reclamo r left join cliente c
on r.cod_afectado = c.cod_usuario group by afectado;


CREATE VIEW CANTIDAD_RESERVA_ESTADO as
select e.Estado,COUNT(e.cod_estadoreserva) as Cantidad from historial_reserva hr inner join reserva_paseo r
on hr.nro_ticket = r.nro_ticket inner join estado_reserva e
on r.cod_estadoreserva = e.cod_estadoreserva group by e.estado;

#-------------------------------------------

/*SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE table historial_reserva; 
SET FOREIGN_KEY_CHECKS = 1;*/
