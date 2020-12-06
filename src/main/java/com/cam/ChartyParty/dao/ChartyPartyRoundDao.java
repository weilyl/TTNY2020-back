package com.cam.ChartyParty.dao;

import com.cam.ChartyParty.dto.Round;
import java.util.List;

/**
 *
 * @author chelseamiller
 */
public interface ChartyPartyRoundDao {

    Round add(Round round);

    Round findById(int id) throws DataNotFoundException;

    List<Round> findRoundsByGameId(int gameId) throws DataNotFoundException;

    boolean update(Round round);

    boolean deleteById(int id);
}
