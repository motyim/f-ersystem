-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ER_System
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ER_System
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ER_System` DEFAULT CHARACTER SET utf8 ;
USE `ER_System` ;

-- -----------------------------------------------------
-- Table `ER_System`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ER_System`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(40) NOT NULL,
  `phoneNumber` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ER_System`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ER_System`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `region` VARCHAR(45) NULL,
  `phonenumber` VARCHAR(11) NOT NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ER_System`.`incident`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ER_System`.`incident` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL,
  `date` DATETIME NULL,
  `region` VARCHAR(45) NULL,
  `location` VARCHAR(45) NULL,
  `descrption` TEXT NULL,
  `image` VARCHAR(400) NULL,
  `employee_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_incident_employee_idx` (`employee_id` ASC),
  INDEX `fk_incident_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_incident_employee`
    FOREIGN KEY (`employee_id`)
    REFERENCES `ER_System`.`employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_incident_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ER_System`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
