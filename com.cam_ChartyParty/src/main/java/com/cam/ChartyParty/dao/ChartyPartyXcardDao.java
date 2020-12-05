package com.cam.ChartyParty.dao;

import com.cam.ChartyParty.dto.Xcard;
import java.util.List;

/**
 *
 * @author chelseamiller
 */
public interface ChartyPartyXcardDao {

    Xcard getXcardById(int xCardId);

    List<Xcard> getAllXcards();

}
