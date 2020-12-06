INSERT INTO `w24ndqvhdse3h5xv`.`user` (`UID`, `username`) VALUES ('123456', 'Smoke');
INSERT INTO `w24ndqvhdse3h5xv`.`user` (`UID`, `username`) VALUES ('567890', 'Sam');
INSERT INTO `w24ndqvhdse3h5xv`.`user` (`UID`, `username`) VALUES ('469033', 'Schuyler');
INSERT INTO `w24ndqvhdse3h5xv`.`user` (`UID`, `username`) VALUES ('892733', 'Siobhan');

INSERT INTO `w24ndqvhdse3h5xv`.`xcard` (`xCardId`, `text`) VALUES ('1', 'Aliens Invade the USA');
INSERT INTO `w24ndqvhdse3h5xv`.`xcard` (`xCardId`, `text`) VALUES ('2', 'Kublai Khan Invades');
INSERT INTO `w24ndqvhdse3h5xv`.`xcard` (`xCardId`, `text`) VALUES ('3', 'UFO by Location');
INSERT INTO `w24ndqvhdse3h5xv`.`xcard` (`xCardId`, `text`) VALUES ('4', 'Favorite Food');
INSERT INTO `w24ndqvhdse3h5xv`.`xcard` (`xCardId`, `text`) VALUES ('5', 'Project Blue Book');
INSERT INTO `w24ndqvhdse3h5xv`.`xcard` (`xCardId`, `text`) VALUES ('6', 'Mongols are Invading');
INSERT INTO `w24ndqvhdse3h5xv`.`xcard` (`xCardId`, `text`) VALUES ('7', 'British are Invading');
INSERT INTO `w24ndqvhdse3h5xv`.`xcard` (`xCardId`, `text`) VALUES ('8', 'French are Invading');

INSERT INTO `w24ndqvhdse3h5xv`.`ycard` (`yCardId`, `text`) VALUES ('1', 'Elephant');
INSERT INTO `w24ndqvhdse3h5xv`.`ycard` (`yCardId`, `text`) VALUES ('2', 'Cats');
INSERT INTO `w24ndqvhdse3h5xv`.`ycard` (`yCardId`, `text`) VALUES ('3', 'Potato');
INSERT INTO `w24ndqvhdse3h5xv`.`ycard` (`yCardId`, `text`) VALUES ('4', 'Lion');
INSERT INTO `w24ndqvhdse3h5xv`.`ycard` (`yCardId`, `text`) VALUES ('5', 'Ostrich');
INSERT INTO `w24ndqvhdse3h5xv`.`ycard` (`yCardId`, `text`) VALUES ('6', 'OompaLoompa');
INSERT INTO `w24ndqvhdse3h5xv`.`ycard` (`yCardId`, `text`) VALUES ('7', 'Tiger');

INSERT INTO `w24ndqvhdse3h5xv`.`game` (`roundsCompleted`, `gameWinner`, `nextJudge`, `entrycode`, `startTime`) VALUES ('3', NULL, '123456', '123ABC', '1425744000000');
INSERT INTO `w24ndqvhdse3h5xv`.`game` (`roundsCompleted`, `gameWinner`, `nextJudge`, `entrycode`,  `startTime`) VALUES ('2', NULL, '567890', 'RESPCT', '1425744000000');

INSERT INTO `w24ndqvhdse3h5xv`.`round` (`gameId`, `roundWinner`, `roundXCard`, `roundYCard`, `endTime`) VALUES ('1', '123456', '1', '3', '1425744000000');
INSERT INTO `w24ndqvhdse3h5xv`.`round` (`gameId`, `roundWinner`, `roundXCard`, `roundYCard`, `endTime`) VALUES ('1', '469033', '2', '4', '1425744000000');
INSERT INTO `w24ndqvhdse3h5xv`.`round` (`gameId`, `roundWinner`, `roundXCard`, `roundYCard`, `endTime`) VALUES ('1', '892733', '3', '2', '1425744000000');
INSERT INTO `w24ndqvhdse3h5xv`.`round` (`gameId`, `roundWinner`, `roundXCard`, `roundYCard`, `endTime`) VALUES ('2', '469033', '4', '5', '1425744000000');
INSERT INTO `w24ndqvhdse3h5xv`.`round` (`gameId`, `roundWinner`, `roundXCard`, `roundYCard`, `endTime`) VALUES ('2', '123456', '5', '7', '1425744000000');

INSERT INTO `w24ndqvhdse3h5xv`.`gameuserycard` (`user_UID`, `game_gameId`, `ycard_yCardId`) VALUES ('123456', '1', '3');
INSERT INTO `w24ndqvhdse3h5xv`.`gameuserycard` (`user_UID`, `game_gameId`, `ycard_yCardId`) VALUES ('123456', '1', '4');
INSERT INTO `w24ndqvhdse3h5xv`.`gameuserycard` (`user_UID`, `game_gameId`, `ycard_yCardId`) VALUES ('567890', '1', '1');
INSERT INTO `w24ndqvhdse3h5xv`.`gameuserycard` (`user_UID`, `game_gameId`, `ycard_yCardId`) VALUES ('567890', '1', '2');
INSERT INTO `w24ndqvhdse3h5xv`.`gameuserycard` (`user_UID`, `game_gameId`, `ycard_yCardId`) VALUES ('469033', '1', '6');
INSERT INTO `w24ndqvhdse3h5xv`.`gameuserycard` (`user_UID`, `game_gameId`, `ycard_yCardId`) VALUES ('469033', '1', '5');
INSERT INTO `w24ndqvhdse3h5xv`.`gameuserycard` (`user_UID`, `game_gameId`, `ycard_yCardId`) VALUES ('892733', '2', '1');
INSERT INTO `w24ndqvhdse3h5xv`.`gameuserycard` (`user_UID`, `game_gameId`, `ycard_yCardId`) VALUES ('892733', '2', '3');
INSERT INTO `w24ndqvhdse3h5xv`.`gameuserycard` (`user_UID`, `game_gameId`, `ycard_yCardId`) VALUES ('892733', '2', '4');
INSERT INTO `w24ndqvhdse3h5xv`.`gameuserycard` (`user_UID`, `game_gameId`, `ycard_yCardId`) VALUES ('892733', '2', '5');

