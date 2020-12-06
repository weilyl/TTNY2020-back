
package com.cam.ChartyParty.dao;

import com.cam.ChartyParty.dto.Ycard;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chelseamiller
 */
@Repository
public class ChartyPartyYcardDatabaseDao implements ChartyPartyYcardDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Ycard> getAll() throws DataNotFoundException {

        final String sql = "SELECT y.yCardId, y.text FROM ycard y;";
        List<Ycard> allYcards = jdbcTemplate.query(sql, new YcardMapper());

        if (allYcards.isEmpty()) {
            throw new DataNotFoundException("No Ycards have been created yet.");
        }
        return allYcards;
    }

    @Override
    public Ycard findById(String id) throws DataNotFoundException {

        Ycard ycard = null;

        final String sql = "SELECT yCardId, text"
                + "FROM ycard WHERE yCardId = ?;";
        try {

            ycard = jdbcTemplate.queryForObject(sql, new YcardMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new DataNotFoundException("Ycard with ID# " + id + " does not exist.");
        }

        return ycard;

    }

    public static final class YcardMapper implements RowMapper<Ycard> {

        @Override
        public Ycard mapRow(ResultSet rs, int index) throws SQLException {
            Ycard ycard = new Ycard();
            ycard.setId(rs.getInt("yCardId"));
            ycard.setText(rs.getString("text"));

            return ycard;
        }

    }
}
