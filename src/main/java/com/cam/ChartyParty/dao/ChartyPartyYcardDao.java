
package com.cam.ChartyParty.dao;

import com.cam.ChartyParty.dto.Ycard;
import java.util.List;

/**
 *
 * @author chelseamiller
 */
public interface ChartyPartyYcardDao {
        
    List<Ycard> getAll() throws DataNotFoundException;

    Ycard findById(String id) throws DataNotFoundException;
}
