package com.cam.ChartyParty.dao;

import com.cam.ChartyParty.dto.Game;
import java.util.List;

/**
 *
 * @author chelseamiller
 */
public interface ChartyPartyGameDao {

    Game add(Game game);

    List<Game> getAll() throws DataNotFoundException;

    Game findById(int id) throws DataNotFoundException;

    
    boolean update(Game game);

    
    boolean deleteById(int id);
}

