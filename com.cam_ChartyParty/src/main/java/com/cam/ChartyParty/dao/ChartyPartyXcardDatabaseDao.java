package com.cam.ChartyParty.dao;

import com.cam.ChartyParty.dto.Xcard;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chelseamiller
 */
@Repository
public class ChartyPartyXcardDatabaseDao implements ChartyPartyXcardDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Xcard getXcardById(int xCardId) {
        try {
            final String SELECT_XCARD_BY_ID = "SELECT * FROM xcard "
                    + "WHERE xCardId = ?";
            Xcard xcard = jdbc.queryForObject(SELECT_XCARD_BY_ID, new XcardMapper(), xCardId);
            return xcard;
        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    public List<Xcard> getAllXcards() {
        final String SELECT_XCARDS = "SELECT * FROM xcard";

        List<Xcard> allXcards = jdbc.query(SELECT_XCARDS, new XcardMapper());

        return allXcards;
    }

    public static final class XcardMapper implements RowMapper<Xcard> {

        @Override
        public Xcard mapRow(ResultSet rs, int i) throws SQLException {
            Xcard xcard = new Xcard();
            xcard.setId(rs.getInt("xCardId"));
            xcard.setText(rs.getString("text"));
            return xcard;
        }
    }

}
