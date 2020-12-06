package com.cam.ChartyParty.dao;

import com.cam.ChartyParty.dto.Game;
import java.util.List;

/**
 *
 * @author chelseamiller
 */
public interface ChartyPartyGameDao {

    Game add(Game game);

    Game findByEntryCode(String entryCode) throws DataNotFoundException;

    boolean update(Game game);


    void deleteById(int id);

}
