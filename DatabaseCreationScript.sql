USE `w24ndqvhdse3h5xv` ;

CREATE TABLE IF NOT EXISTS `w24ndqvhdse3h5xv`.`user` (
  `UID` VARCHAR(50) NOT NULL,
  `username` VARCHAR(45),
  PRIMARY KEY (`UID`));


CREATE TABLE IF NOT EXISTS `w24ndqvhdse3h5xv`.`xcard` (
  `xCardId` INT AUTO_INCREMENT NOT NULL,
  `text` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`xCardId`),
  UNIQUE INDEX `text_UNIQUE` (`text` ASC));


CREATE TABLE IF NOT EXISTS `w24ndqvhdse3h5xv`.`ycard` (
  `yCardId` INT AUTO_INCREMENT NOT NULL,
  `text` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`yCardId`),
  UNIQUE INDEX `text_UNIQUE` (`text` ASC));


CREATE TABLE IF NOT EXISTS `w24ndqvhdse3h5xv`.`game` (
    `gameId` INT AUTO_INCREMENT NOT NULL,
    `roundsCompleted` INT NULL,
    `gameWinner` VARCHAR(50) NULL,
    `nextJudge` VARCHAR(50) NULL,
    `entrycode` char(6) NULL,
   `startTime` BIGINT(19) NOT NULL,
    PRIMARY KEY (`gameId`),
    FOREIGN KEY (`gameWinner`)
        REFERENCES `w24ndqvhdse3h5xv`.`user` (`UID`),
    FOREIGN KEY (`nextJudge`)
        REFERENCES `w24ndqvhdse3h5xv`.`user` (`UID`)
);

-- ALTER TABLE `w24ndqvhdse3h5xv`.`game` AUTO_INCREMENT = 10000;


CREATE TABLE IF NOT EXISTS `w24ndqvhdse3h5xv`.`round` (
  `roundId` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `gameId` INT NOT NULL,
  `roundWinner` VARCHAR(50) NULL,
  `roundXCard` INT NULL,
  `roundYCard` INT NULL,
  `endTime` BIGINT(19) NOT NULL,
  INDEX `fk_round_game1_idx` (`gameId` ASC) ,
  INDEX `fk_round_user1_idx` (`roundWinner` ASC) ,
  INDEX `fk_round_xcard1_idx` (`roundXCard` ASC) ,
  INDEX `fk_round_ycard1_idx` (`roundYCard` ASC) ,
  CONSTRAINT `fk_round_game1`
    FOREIGN KEY (`gameId`)
    REFERENCES `w24ndqvhdse3h5xv`.`game` (`gameId`),
  CONSTRAINT `fk_round_user1`
    FOREIGN KEY (`roundWinner`)
    REFERENCES `w24ndqvhdse3h5xv`.`user` (`UID`),
  CONSTRAINT `fk_round_xcard1`
    FOREIGN KEY (`roundXCard`)
    REFERENCES `w24ndqvhdse3h5xv`.`xcard` (`xCardId`),
  CONSTRAINT `fk_round_ycard1`
    FOREIGN KEY (`roundYCard`)
    REFERENCES `w24ndqvhdse3h5xv`.`ycard` (`yCardId`));


CREATE TABLE IF NOT EXISTS `w24ndqvhdse3h5xv`.`gameuserycard` (
  `user_UID` VARCHAR(50) NOT NULL,
  `game_gameId` INT NOT NULL,
  `ycard_yCardId` INT NOT NULL,
  PRIMARY KEY (`user_UID`, `ycard_yCardId`, `game_gameId`),
  INDEX `fk_gameuserycard_game1_idx` (`game_gameId` ASC),
  INDEX `fk_gameuserycard_ycard1_idx` (`ycard_yCardId` ASC),
  CONSTRAINT `fk_gameuserycard_user`
    FOREIGN KEY (`user_UID`)
    REFERENCES `w24ndqvhdse3h5xv`.`user` (`UID`),
  CONSTRAINT `fk_gameuserycard_game1`
    FOREIGN KEY (`game_gameId`)
    REFERENCES `w24ndqvhdse3h5xv`.`game` (`gameId`),
  CONSTRAINT `fk_gameuserycard_ycard1`
    FOREIGN KEY (`ycard_yCardId`)
    REFERENCES `w24ndqvhdse3h5xv`.`ycard` (`yCardId`));