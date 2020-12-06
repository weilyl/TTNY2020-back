package com.cam.ChartyParty.dao;

import java.util.List;

/**
 *
 * @author Shazena Khan
 *
 * Date Created: Dec 5, 2020
 */
public interface ChartyPartyOperationDao {

    void assignCardsToUsersStartGame(int gameId, List<String> UIDs, List<Integer> yCardIds);

    void endRoundTakeCardsFromUsers(int gameId, List<Integer> cardIds);

}
