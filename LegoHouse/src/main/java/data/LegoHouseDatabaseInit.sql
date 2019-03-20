DROP TABLE IF EXISTS `legoHouse`.`orders`;
DROP TABLE IF EXISTS `legoHouse`.`users`;

CREATE TABLE `legoHouse`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(90) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` ENUM('CUSTOMER','EMPLOYEE') NOT NULL DEFAULT 'CUSTOMER',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
);

CREATE TABLE `legoHouse`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userid` INT NOT NULL,
  `width` INT NOT NULL,
  `length` INT NOT NULL,
  `height` INT NOT NULL,
  `date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `shipped` BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (`id`),
  INDEX `userId_FK_idx` (`userid` ASC) VISIBLE,
  CONSTRAINT `userId_FK`
    FOREIGN KEY (`userid`)
    REFERENCES `legoHouse`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);