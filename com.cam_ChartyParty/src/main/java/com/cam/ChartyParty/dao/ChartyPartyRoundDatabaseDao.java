package com.cam.ChartyParty.dao;

import com.cam.ChartyParty.dto.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
@Profile("database")
public class ChartyPartyRoundDatabaseDao implements ChartyPartyRoundDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ChartyPartyRoundDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Get all rounds from Game
    /*
    @Override
    public List<Round> findRoundsByGameId(int gameId) {

        final String sql = "SELECT id, TimeOfGuess, Guess, Score "
                + "FROM Round "
                + "WHERE Game_id = ?"
                + " ORDER BY TimeOfGuess DESC;";

        return jdbcTemplate.query(sql, new RoundMapper(), gameId);
    }
     */
    @Override
    public List<Round> findRoundsByGameId(int gameId) throws DataNotFoundException {
        final String sql = "SELECT id, Game_id, TimeOfGuess, Guess, Score FROM Round;";
        List<Round> allRounds = jdbcTemplate.query(sql, new RoundMapper());
        List<Round> roundsFromGame = new ArrayList<>();
        for (Round i : allRounds) {
            if (i.getGameId() == gameId) {
                roundsFromGame.add(i);
            }
        }
        if (roundsFromGame.size() == 0) {
            throw new DataNotFoundException("No rounds found for game id# " + gameId);
        }

        return roundsFromGame;
    }

    //CRUD FUNCTIONS (irrelevant to game play) 
    @Override
    public Round add(Round round) {
        final String sql = "INSERT INTO Round(Game_id, TimeOfGuess, Guess, Score) VALUES(?,?,?,?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, round.getGameId());
            statement.setString(2, round.getTimestamp());
            statement.setString(3, round.getGuess());
            statement.setString(4, round.getResult());

            return statement;

        }, keyHolder);

        round.setId(keyHolder.getKey().intValue());

        return round;
    }

    @Override
    public List<Round> getAll() throws DataNotFoundException {
        final String sql = "SELECT id, Game_id, TimeOfGuess, Guess, Score FROM Round;";
        List<Round> allRounds= jdbcTemplate.query(sql, new RoundMapper());
        
        if(allRounds.isEmpty()){
            throw new DataNotFoundException("No rounds exist.");
        }
        
        return allRounds;
    }

    @Override
    public Round findById(int id) throws DataNotFoundException {
        Round round = new Round();
        final String sql = "SELECT id, Game_id, TimeOfGuess, Guess, Score "
                + "FROM Round "
                + "WHERE id = ?;";

        round=jdbcTemplate.queryForObject(sql, new RoundMapper(), id);
        
        if(round==null){
            throw new DataNotFoundException("Round id#"+ id+" does not exist.");
        }
        
        return round;
    }

    @Override
    public boolean update(Round round) {
        final String sql = "UPDATE Round SET "
                + "Game_id = ?, "
                + "TimeOfGuess = ?, "
                + "Guess = ?, "
                + "Score = ? "
                + "WHERE id = ?;";

        return jdbcTemplate.update(sql,
                round.getGameId(),
                round.getTimestamp(),
                round.getGuess(),
                round.getResult(),
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

            round.setId(rs.getInt("id"));
            round.setGameId(rs.getInt("Game_id"));
            round.setTimestamp(rs.getString("TimeOfGuess"));
            round.setGuess(rs.getString("Guess"));
            round.setResult(rs.getString("Score"));

            return round;
        }

    }

}
