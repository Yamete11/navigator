-- MySQL Script with Cascading Deletions

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema navigator
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `navigator` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `navigator` ;

-- -----------------------------------------------------
-- Table `navigator`.`Cities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `navigator`.`Cities` ;

CREATE TABLE IF NOT EXISTS `navigator`.`Cities` (
                                                    `city_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                    `title` VARCHAR(45) NOT NULL,
    `x` DOUBLE NOT NULL,
    `y` DOUBLE NOT NULL,
    PRIMARY KEY (`city_id`),
    UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `navigator`.`City_connections`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `navigator`.`City_connections` ;

CREATE TABLE IF NOT EXISTS `navigator`.`City_connections` (
                                                              `city_connection_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                              `distance` DOUBLE NOT NULL,
                                                              `first_city_id` BIGINT NOT NULL,
                                                              `second_city_Id` BIGINT NOT NULL,
                                                              PRIMARY KEY (`city_connection_id`),
    INDEX `fk_CityConnections_City_idx` (`first_city_id` ASC) VISIBLE,
    INDEX `fk_CityConnections_City1_idx` (`second_city_Id` ASC) VISIBLE,
    CONSTRAINT `fk_CityConnections_City`
    FOREIGN KEY (`first_city_id`)
    REFERENCES `navigator`.`Cities` (`city_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_CityConnections_City1`
    FOREIGN KEY (`second_city_Id`)
    REFERENCES `navigator`.`Cities` (`city_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `navigator`.`Start_locations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `navigator`.`Start_locations` ;

CREATE TABLE IF NOT EXISTS `navigator`.`Start_locations` (
                                                             `start_location_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                             `city_id` BIGINT NOT NULL,
                                                             PRIMARY KEY (`start_location_id`),
    INDEX `fk_Start_locations_Cities1_idx` (`city_id` ASC) VISIBLE,
    CONSTRAINT `fk_Start_locations_Cities1`
    FOREIGN KEY (`city_id`)
    REFERENCES `navigator`.`Cities` (`city_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `navigator`.`End_locations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `navigator`.`End_locations` ;

CREATE TABLE IF NOT EXISTS `navigator`.`End_locations` (
                                                           `end_location_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                           `city_id` BIGINT NOT NULL,
                                                           PRIMARY KEY (`end_location_id`),
    INDEX `fk_End_locations_Cities1_idx` (`city_id` ASC) VISIBLE,
    CONSTRAINT `fk_End_locations_Cities1`
    FOREIGN KEY (`city_id`)
    REFERENCES `navigator`.`Cities` (`city_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `navigator`.`Routes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `navigator`.`Routes` ;

CREATE TABLE IF NOT EXISTS `navigator`.`Routes` (
                                                    `route_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                    `total_distance` DOUBLE NOT NULL,
                                                    `start_location_id` BIGINT NOT NULL,
                                                    `end_location_id` BIGINT NOT NULL,
                                                    PRIMARY KEY (`route_id`),
    INDEX `fk_Routes_Start_locations1_idx` (`start_location_id` ASC) VISIBLE,
    INDEX `fk_Routes_End_locations1_idx` (`end_location_id` ASC) VISIBLE,
    CONSTRAINT `fk_Routes_Start_locations1`
    FOREIGN KEY (`start_location_id`)
    REFERENCES `navigator`.`Start_locations` (`start_location_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Routes_End_locations1`
    FOREIGN KEY (`end_location_id`)
    REFERENCES `navigator`.`End_locations` (`end_location_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `navigator`.`Route_cities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `navigator`.`Route_cities` ;

CREATE TABLE IF NOT EXISTS `navigator`.`Route_cities` (
                                                          `route_cities_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                          `city_id` BIGINT NOT NULL,
                                                          `route_id` BIGINT NOT NULL,
                                                          `order_index` INT NOT NULL,
                                                          PRIMARY KEY (`route_cities_id`),
    INDEX `fk_Route_cities_Cities1_idx` (`city_id` ASC) VISIBLE,
    INDEX `fk_Route_cities_Routes1_idx` (`route_id` ASC) VISIBLE,
    CONSTRAINT `fk_Route_cities_Cities1`
    FOREIGN KEY (`city_id`)
    REFERENCES `navigator`.`Cities` (`city_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Route_cities_Routes1`
    FOREIGN KEY (`route_id`)
    REFERENCES `navigator`.`Routes` (`route_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
