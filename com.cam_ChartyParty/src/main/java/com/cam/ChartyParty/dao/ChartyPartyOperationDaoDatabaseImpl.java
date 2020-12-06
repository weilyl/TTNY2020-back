package com.cam.ChartyParty.dao;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Shazena Khan
 *
 * Date Created: Dec 5, 2020
 */
@Repository
public class ChartyPartyOperationDaoDatabaseImpl implements ChartyPartyOperationDao {

    @Autowired
    JdbcTemplate jdbc;

    public final int numberOfRoundsInGame = 5;

    @Override
    @Transactional
    public void assignCardsToUsersStartGame(int gameId, List<String> UIDs, List<Integer> yCardIds) {

        Collections.shuffle(yCardIds);

        final String START_GAME = "INSERT INTO `gameuserycard` "
                + "(`user_UID`, `game_gameId`, `ycard_yCardId`) "
                + "VALUES (?, ?, ?);";

        for (int i = 0; i < UIDs.size(); i++) {
            for (int j = 0; j < numberOfRoundsInGame; j++) {
                jdbc.update(START_GAME, UIDs.get(i), gameId, yCardIds.get(j));
            }
        }
    }

    @Override
    @Transactional
    public void endRoundTakeCardsFromUsers(int gameId, List<Integer> cardIds) {
        final String DELETE_FROM_GAMEUSERYCARD = "DELETE FROM gameuserycard "
                + "WHERE game_gameId = ? AND ycard_yCardId = ?";

        for (Integer cardId : cardIds) {
            jdbc.update(DELETE_FROM_GAMEUSERYCARD, gameId, cardId);
        }
    }

}
