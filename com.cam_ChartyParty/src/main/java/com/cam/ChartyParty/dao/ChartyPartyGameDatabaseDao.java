package com.cam.ChartyParty.dao;

import com.cam.ChartyParty.dto.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chelseamiller
 */
@Repository
@Profile("database")
public class ChartyPartyGameDatabaseDao implements ChartyPartyGameDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Game add(Game game) {
        final String sql = "INSERT INTO game(entrycode, roundsCompleted, gameWinner, nextJudge) VALUES(?,?,?,?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getEntrycode());
            statement.setInt(2, game.getRoundscompleted());
            statement.setString(3, game.getWinneruserid());
            statement.setString(4, game.getNextjudgeid());
            return statement;

        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());

        return game;
    }


    @Override
    public Game findByEntryCode(String entryCode) throws DataNotFoundException {
        Game game = null;

        final String sql = "SELECT gameId, entrycode, roundsCompleted, gameWinner, nextJudge "
                + "FROM game WHERE entrycode = ?;";
        try {

            game = jdbcTemplate.queryForObject(sql, new GameMapper(), entryCode);
        } catch (EmptyResultDataAccessException ex) {
            throw new DataNotFoundException("Game with that entry code does not exist.");
        }

        return game;
    }

    @Override
    public boolean update(Game game) {
        final String sql = "UPDATE game SET "
                + "roundsCompleted = ?, "
                + "gameWinner = ? "
                + "nextJudge = ? "
                + "WHERE gameId = ?;";

        return jdbcTemplate.update(sql,
                game.getRoundscompleted(),
                game.getWinneruserid(),
                game.getNextjudgeid(),
                game.getId()) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        
        final String sql = "DELETE FROM game WHERE gameId = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            String winner=rs.getString("gameWinner");
            if(winner.isEmpty()){
                winner="";
            }
            Game game = new Game();
            game.setId(rs.getInt("id"));
            game.setEntrycode(rs.getString("entrycode"));
            game.setRoundscompleted(rs.getInt("roundsCompleted"));
            game.setWinneruserid(winner);
            game.setNextjudgeid(rs.getString("nextJudge"));
           

            return game;
        }

    }

}
