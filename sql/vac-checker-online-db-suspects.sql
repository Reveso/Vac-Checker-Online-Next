CREATE DATABASE IF NOT EXISTS `vac_checker_database`;
USE `vac_checker_database`;

DROP TABLE IF EXISTS `suspects`;

CREATE TABLE `suspects` (
	`steamid` VARCHAR(50) NOT NULL,
    `username` VARCHAR(50) NOT NULL,
	`vac_status` VARCHAR(7) DEFAULT "loading",
    `number_of_bans_when_added` INT(10) DEFAULT 0,
	`description` TEXT DEFAULT NULL,
    `addition_date` DATETIME DEFAULT NULL,
    PRIMARY KEY (`steamid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


insert into `suspects` values("76561198039345837", "Moretos", "loading", 0, "fsaiojfsaio", NULL);