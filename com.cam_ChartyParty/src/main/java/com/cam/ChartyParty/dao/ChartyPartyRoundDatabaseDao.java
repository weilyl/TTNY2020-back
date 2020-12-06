package com.cam.ChartyParty.dao;

import com.cam.ChartyParty.dto.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chelseamiller
 */
@Repository
public class ChartyPartyRoundDatabaseDao implements ChartyPartyRoundDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ChartyPartyRoundDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Round> findRoundsByGameId(int gameId) throws DataNotFoundException {
        final String sql = "SELECT roundId, gameId, roundWinner, roundXCard, roundYCard "
                + "FROM round"
                + "WHERE gameId = ?;";
        List<Round> allRounds = jdbcTemplate.query(sql, new RoundMapper(), gameId);

        if (allRounds.size() == 0) {
            throw new DataNotFoundException("No rounds found for game id# " + gameId);
        }

        return allRounds;
    }

    @Override
    public Round add(Round round) {
        final String sql = "INSERT INTO Round(gameId, roundXCard) VALUES(?,?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, round.getGameid());
            statement.setInt(2, round.getXcardid());

            return statement;

        }, keyHolder);

        round.setId(keyHolder.getKey().intValue());

        return round;
    }

    @Override
    public Round findById(int id) throws DataNotFoundException {
        Round round = new Round();
        final String sql = "SELECT roundId, gameId, roundWinner, roundXCard, roundYCard "
                + "FROM round "
                + "WHERE roundId = ?;";

        round = jdbcTemplate.queryForObject(sql, new RoundMapper(), id);

        if (round == null) {
            throw new DataNotFoundException("Round id#" + id + " does not exist.");
        }

        return round;
    }

    @Override
    public boolean update(Round round) {
        final String sql = "UPDATE Round SET "
                + "roundWinner = ?, "
                + "roundYCard = ? "
                + "WHERE roundId = ?;";

        return jdbcTemplate.update(sql,
                round.getWinneruserid(),
                round.getWinnerycardid(),
             
                round.getId()) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        final String sql = "DELETE FROM Round WHERE id = ?;";
        return jdbcTemplate.update(sql, id) > 0;

    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();

            round.setId(rs.getInt("roundId"));
            round.setGameid(rs.getInt("gameId"));
            round.setWinneruserid(rs.getString("roundWinner"));
            round.setWinnerycardid(rs.getInt("roundYCard"));
            round.setXcardid(rs.getInt("roundXCard"));

            return round;
        }

    }

}
